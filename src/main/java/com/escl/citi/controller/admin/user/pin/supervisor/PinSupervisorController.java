package com.escl.citi.controller.admin.user.pin.supervisor;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.data.dto.UserDto;
import com.escl.citi.data.mapper.UserDtoMapper;
import com.escl.citi.entity.User;
import com.escl.citi.exception.AdminNotAllowedToDeleteHimselfException;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.exception.ImageWrongScaleException;
import com.escl.citi.exception.MakerCheckerViolationException;
import com.escl.citi.service.Role.RoleService;
import com.escl.citi.service.user.UserService;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
import com.escl.citi.validation.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.websocket.server.PathParam;


@Controller
@PreAuthorize("hasAuthority('Administrator') or hasAuthority('Developer')")
@RequestMapping(value = "/admin/pin-supervisor")
public class PinSupervisorController extends AbstractPublishController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserDtoMapper userDtoMapper;

    @Autowired
    private UserValidator userValidator;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/pin-supervisor";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "pin-supervisor.title";
    }


    @RequestMapping("")
    public String index(PageSort pageSort, Model model) {
        Page<User> pinSupervisorPage = userService.findAllPinSupervisors(pageSort.getPage(model));
        model.addAttribute("page", pinSupervisorPage);

        return "users.pin.supervisors.index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());

        return "user.pin.supervisors.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        User pinSupervisor = userService.findById(id);
        pinSupervisor.setPassword(null);

        model.addAttribute("user", pinSupervisor);

        return "user.pin.supervisors.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        userValidator.validate(userDto, result);

        if (result.hasErrors())
            return "user.pin.supervisors.form";

        if (userDto.getId() != null && userDto.getPassword().isEmpty()) {
            User user = userService.findById(userDto.getId());
            userDto.setPassword(user.getPassword());
            user = userDtoMapper.map(userDto);
            user.setRole(roleService.findById(User.RoleName.PIN_SUPERVISOR_ROLE.getValue()));
            userService.updateWithOldPassword(user);
        } else {
            User pinSupervisor = userDtoMapper.map(userDto);
            pinSupervisor.setRole(roleService.findById(User.RoleName.PIN_SUPERVISOR_ROLE.getValue()));
            userService.save(pinSupervisor);
        }

        Flash.success(redirectAttributes);

        return "redirect:/admin/pin-supervisor";
    }

    @RequestMapping(value = "/lock/{id}")
    public String lockOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            userService.lock(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/pin-supervisor";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/pin-supervisor";
        }

        return "redirect:/admin/pin-supervisor";
    }


    @PostMapping(value = "/checkbox")
    public String massLock(
            @PathParam("ids") Integer ids[],
            @PathParam("action") String action,
            final RedirectAttributes redirectAttributes) {

        try {
            userService.massAction(ids, action);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/pin-supervisor";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/pin-supervisor";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/pin-supervisor";
    }

    @RequestMapping(value = "/check/{id}")
    public String check(@PathVariable("id") Integer id, final RedirectAttributes redirectAttributes) {


        try {
            userService.check(id);
        } catch (MakerCheckerViolationException e) {
            Flash.error(redirectAttributes, "Maker nie może być Checker'em");
            return "redirect:/admin/pin-supervisor";
        }

        Flash.success(redirectAttributes, "Akcja zakończona powodzeniem");
        return "redirect:/admin/pin-supervisor";
    }

}
