package com.escl.citi.controller;

import com.escl.citi.entity.User;
import com.escl.citi.security.AuthManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractPublishController {

    @Autowired
    private AuthManager authManager;

    @ModelAttribute("title")
    public String setTitle() {
        return "dashboard.title";
    }

    @GetMapping("")
    public String index() {
        User user = authManager.user();

        if(user.getRole() != null && user.getRole().getName().equals("Dealer")){
            return "dashboard";
        }

        return "redirect:/admin/dashboard";
    }

}
