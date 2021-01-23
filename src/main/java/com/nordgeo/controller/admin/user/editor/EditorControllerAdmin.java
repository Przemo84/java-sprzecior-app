package com.nordgeo.controller.admin.admin.editor;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.data.mapper.UserDtoMapper;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.service.Role.RoleService;
import com.nordgeo.service.user.UserService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import com.nordgeo.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/admin/editors")
public class EditorControllerAdmin extends AdminAbstractController {

    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/editors";
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
    public String indexEditors(PageSort pageSort, Model model) {
        Page<User> editorPage = userService.findAllEditors(pageSort.getPage(model));
        model.addAttribute("page", editorPage);

        return "editors.index";
    }

    @RequestMapping(value = "/make-employee/{id}")
    public String makeAsEmployee(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {

        try {
            userService.changeRole(id, User.RoleName.EMPLOYEE_ROLE);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
            return "redirect:/admin/editors";
        }

        return "redirect:/admin/editors";
    }

}
