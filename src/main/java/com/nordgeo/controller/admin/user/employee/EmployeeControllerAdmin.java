package com.nordgeo.controller.admin.user.employee;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.data.dto.UserDto;
import com.nordgeo.data.mapper.UserDtoMapper;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminNotAllowedToDeleteHimselfException;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.exception.ItemNotFoundException;
import com.nordgeo.service.Role.RoleService;
import com.nordgeo.service.user.UserService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import com.nordgeo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin/employees")
public class EmployeeControllerAdmin extends AdminAbstractController {

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
        return "/admin/employees";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "employee.title";
    }


    @RequestMapping("")
    public String index(PageSort pageSort, Model model) {
        Page<User> employeePage = userService.findAllEmployees(pageSort.getPage(model));
        model.addAttribute("page", employeePage);

        return "users.employees.index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());

        return "user.employees.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model,
                       final RedirectAttributes redirectAttributes) {

        try {
            User adminUser = userService.findById(id);
            adminUser.setPassword(null);

            model.addAttribute("user", adminUser);
        } catch (ItemNotFoundException e) {
            Flash.error(redirectAttributes, "Nie znaleziono użytkownika");
            return "redirect:/admin/employees";
        }

        return "user.employees.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        userValidator.validate(userDto, result);

        if (result.hasErrors())
            return "user.employees.form";

        if (userDto.getId() != null && userDto.getPassword().isEmpty()) {
            User user = userService.findById(userDto.getId());
            userDto.setPassword(user.getPassword());
            user = userDtoMapper.map(userDto);
            user.setRole(roleService.findById(User.RoleName.EMPLOYEE_ROLE.getValue()));
            userService.updateWithOldPassword(user);
        } else {
            User employee = userDtoMapper.map(userDto);
            employee.setRole(roleService.findById(User.RoleName.EMPLOYEE_ROLE.getValue()));
            userService.save(employee);
        }

        Flash.success(redirectAttributes);

        return "redirect:/admin/employees";
    }

    @RequestMapping(value = "/lock/{id}")
    public String lockOne(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            userService.lock(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/employees";
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz zablokować samego siebie");
            return "redirect:/admin/employees";
        }

        return "redirect:/admin/employees";
    }


    @RequestMapping(value = "/make-editor/{id}")
    public String upgradeToEditor(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            userService.changeRole(id, User.RoleName.EDITOR_ROLE);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/employees";
        }

        return "redirect:/admin/employees";
    }


}
