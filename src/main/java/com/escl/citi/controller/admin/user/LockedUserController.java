package com.escl.citi.controller.admin.user;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.entity.User;
import com.escl.citi.exception.AdminNotAllowedToDeleteHimselfException;
import com.escl.citi.exception.AdminOperationNotAllowedException;
import com.escl.citi.service.user.UserService;
import com.escl.citi.utils.Flash;
import com.escl.citi.utils.PageSort;
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
public class LockedUserController extends AbstractPublishController {


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
