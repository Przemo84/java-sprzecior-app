package com.escl.citi.controller.admin.role;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.entity.Role;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.service.Role.RoleService;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@PreAuthorize("hasAuthority('Administrator') or hasAuthority('Developer')")
@RequestMapping(value = "/admin/role")
public class RoleController extends AbstractPublishController {

    @Autowired
    private RoleService roleService;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/role";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "role.title";
    }


    @RequestMapping("")
    public String index(PageSort pageSort, Model model) {
        Page<Role> rolePage = roleService.findAll(pageSort.getPage(model));
        model.addAttribute("page", rolePage);

        return "roles.index";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("role", new Role());

        return "role.form";
    }

    @RequestMapping("/form/{id}")
    public String form(@PathVariable("id") int id, Model model) {
        Role role = roleService.findById(id);

        model.addAttribute("role", role);

        return "role.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("role") Role role,
            BindingResult result,
            final RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "role.form";

        roleService.save(role);
        Flash.success(redirectAttributes);

        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteOne(
            @PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        try {
            roleService.delete(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/role";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/role";
    }


}
