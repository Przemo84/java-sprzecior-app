package com.escl.citi.controller.admin.user;


import com.escl.citi.controller.AbstractPublishController;
import com.escl.citi.data.UserPasswordDto;
import com.escl.citi.exception.UserLastSixPasswordException;
import com.escl.citi.service.user.UserService;
import com.escl.citi.utils.Flash;
import com.escl.citi.validation.validator.UserPasswordValidator;
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
public class UserAccountController extends AbstractPublishController {


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
        return "user.change.password.form";
    }

    @PostMapping(value = "/save")
    public String submit(
            @Valid @ModelAttribute("user") UserPasswordDto userPasswordDto,
            BindingResult result, final RedirectAttributes redirectAttributes) {

        userPasswordValidator.validate(userPasswordDto, result);

        if (result.hasErrors())
            return "user.change.password.form";

        try {
            userService.changePassword(userPasswordDto);
        } catch (UserLastSixPasswordException e) {
            Flash.error(redirectAttributes, "Akcja zakończona niepowodzeniem. Nowe hasło znalezione w 6 ostatnich hasłach użytkownika");
            return "redirect:/admin/account/password";
        }

        Flash.success(redirectAttributes);
        return "redirect:/admin/account/password";
    }


}
