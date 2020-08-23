package com.nordgeo.controller.app;


import com.nordgeo.controller.AppAbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/app")
public class DashboardControllerApp extends AppAbstractController {


    @ModelAttribute("moduleBaseUrl")
    public String moduleBaseUrl() {
        return "/app/dashboard";
    }

    @ModelAttribute("title")
    public String setTitle() {
        return "user.title";
    }

    @RequestMapping("/dashboard")
    public String index( HttpSession session) {
        session.invalidate();

        return "app.dashboard.index";
    }
}
