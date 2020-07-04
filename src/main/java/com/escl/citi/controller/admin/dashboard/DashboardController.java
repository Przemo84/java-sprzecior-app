package com.escl.citi.controller.admin.dashboard;


import com.escl.citi.controller.AbstractPublishController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
    public String index(Model model, HttpServletRequest request, HttpSession session) throws ParseException {
        session.invalidate();

        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();

        String date = new SimpleDateFormat("dd, YYYY", new Locale("pl")).format(time);
        String month = new SimpleDateFormat("MMMM", new Locale("pl")).format(time);
        String weekDay = new SimpleDateFormat("EEEE", new Locale("pl")).format(time).toUpperCase();
        String hour = new SimpleDateFormat("HH:mm", new Locale("pl")).format(time);


        return "dashboard.index";
    }
}
