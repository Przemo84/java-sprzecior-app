package com.nordgeo.controller.admin.user;


import com.nordgeo.controller.AdminAbstractController;
import com.nordgeo.data.dto.UserPasswordDto;
import com.nordgeo.service.user.UserService;
import com.nordgeo.utils.Flash;
import com.nordgeo.validators.UserPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Controller
@RequestMapping(value = "/admin/account/")
public class UserAccountControllerAdmin extends AdminAbstractController {


    @ModelAttribute("moduleBaseUrl")
    public String baseUrl() {
        return "/admin/account";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "user.title";
    }

    @Autowired
    UserService userService;

    @Autowired
    UserPasswordValidator userPasswordValidator;


    @RequestMapping("/password")
    public String changePasswordForm(Model model, HttpServletRequest request, final RedirectAttributes redirectAttributes) {

        if (request.getQueryString() !=null) {
            model.addAttribute("user", new UserPasswordDto());
            Flash.error(redirectAttributes, "Wymagana zmiana hasła");
            return "redirect:/admin/account/password";
        }

        model.addAttribute("user", new UserPasswordDto());
        return "admin.user.change.password.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("user") UserPasswordDto userPasswordDto,
            BindingResult result, final RedirectAttributes redirectAttributes) {

        userPasswordValidator.validate(userPasswordDto, result);

        if (result.hasErrors())
            return "admin.user.change.password.form";

        try {
            userService.changePassword(userPasswordDto);
        } catch (Exception e) {
            Flash.error(redirectAttributes, "Akcja zakończona niepowodzeniem.");
            return "redirect:/admin/account/password";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/dashboard";
    }


}
