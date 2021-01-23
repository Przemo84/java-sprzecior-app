package com.nordgeo.controller.admin;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.entity.Role;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.service.Role.RoleService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin/roles")
public class RoleControllerAdmin extends AdminAbstractController {

    @Autowired
    private RoleService roleService;

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/roles";
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

        return "redirect:/admin/roles";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(
            @PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        try {
            roleService.delete(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/roles";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/roles";
    }


}
