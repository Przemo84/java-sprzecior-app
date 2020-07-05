package com.nordgeo.controller.admin.user.admin;


import com.nordgeo.controller.AbstractPublishController;
import com.nordgeo.data.dto.UserDto;
import com.nordgeo.data.mapper.UserDtoMapper;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminNotAllowedToDeleteHimselfException;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.service.Role.RoleService;
import com.nordgeo.service.user.UserService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import com.nordgeo.validation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


@Controller
@RequestMapping(value = "/admin/admins")
public class UserAdminController extends AbstractPublishController {

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/admins";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "user.title";
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @Autowired
    private RoleService roleService;

    @RequestMapping("")
    public String indexAdmins(PageSort pageSort, Model model) {
        Page<User> adminPage = userService.findAllAdmins(pageSort.getPage(model));
        model.addAttribute("page", adminPage);

        return "users.admins.index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new UserDto());

        return "users.admins.form";
    }

    @GetMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        User adminUser = userService.findById(id);
        adminUser.setPassword(null);

        model.addAttribute("user", adminUser);

        return "users.admins.form";
    }

    @PostMapping(value = "/save")
    public String submit(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result,
                         final RedirectAttributes redirectAttributes) {

        userValidator.validate(userDto, result);

        if (result.hasErrors())
            return "users.admins.form";


        if (userDto.getId() != null && userDto.getPassword().isEmpty()) {
            User user = userService.findById(userDto.getId());
            userDto.setPassword(user.getPassword());
            user = userDtoMapper.map(userDto);
            user.setRole(roleService.findById(User.RoleName.ADMIN_ROLE.getValue()));
            userService.updateWithOldPassword(user);
        } else {
            User adminUser = userDtoMapper.map(userDto);
            adminUser.setRole(roleService.findById(User.RoleName.ADMIN_ROLE.getValue()));
            userService.save(adminUser);
        }

        Flash.success(redirectAttributes, "Akcja zakończona powodzeniem.");
        return "redirect:/admin/admins";
    }

    @RequestMapping(value = "/lock/{id}")
    public String lockOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            userService.lock(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/admins";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/admins";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/admins";
    }


    @PostMapping(value = "/checkbox")
    public String massLock(@PathParam("ids") Integer ids[], @PathParam("action") String action,
                           final RedirectAttributes redirectAttributes) {

        try {
            userService.massAction(ids, action);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/admins";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/admins";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/admins";
    }


}
