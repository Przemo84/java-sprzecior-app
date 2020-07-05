package com.escl.citi.controller.admin.dashboard;


import com.escl.citi.controller.AbstractPublishController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/admin")
public class DashboardController extends AbstractPublishController {


    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/admin/dashboard";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "user.title";
    }

    @RequestMapping("/dashboard")
    public String index( HttpSession session) {
        session.invalidate();

        return "dashboard.index";
    }
}
