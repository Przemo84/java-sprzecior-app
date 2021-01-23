package com.nordgeo.controller.admin.admin;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.entity.User;
import com.nordgeo.exception.AdminNotAllowedToDeleteHimselfException;
import com.nordgeo.exception.AdminOperationNotAllowedException;
import com.nordgeo.service.user.UserService;
import com.nordgeo.utils.Flash;
import com.nordgeo.utils.PageSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/admin/locked-users")
public class LockedUserControllerAdmin extends AdminAbstractController {


    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/locked-users";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "user.locked.title";
    }

    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String index(PageSort pageSort, Model model) {
        Page<User> userPage = userService.findAllLocked(pageSort.getPage(model));
        model.addAttribute("page", userPage);

        return "locked.users.index";
    }

    @RequestMapping(value = "/unlock/{id}")
    public String unlock(
            @PathVariable("id") int id,
            final RedirectAttributes redirectAttributes) {

        try {
            userService.unlock(id);
        } catch (AdminOperationNotAllowedException e) {
            Flash.error(redirectAttributes, "Operacja dozwolona tylko dla Administratora");
        } catch (AdminNotAllowedToDeleteHimselfException e) {
            Flash.error(redirectAttributes, "Nie możesz usunąć samego siebie");
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/locked-users";
    }


}
