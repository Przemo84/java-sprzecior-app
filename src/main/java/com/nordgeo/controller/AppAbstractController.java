package com.nordgeo.controller;

import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import com.nordgeo.security.AuthManager;
import com.nordgeo.service.Role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.SimpleDateFormat;
import java.util.Date;

@PreAuthorize("hasAuthority('Admin') or hasAnyAuthority('Employee', 'Editor')")
public abstract class AppAbstractController {


    @ModelAttribute("baseUrl")
    public String baseUrl() {
        return "/app/dashboard";
    }

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthManager authManager;

    @ModelAttribute("roles")
    public Iterable<Role> populateRoles() {
        return roleService.findAll();
    }

    @ModelAttribute("authUser")
    public User populateAuthUser() {
        return authManager.user();
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    protected ModelAndView redirect(String uri) {
        RedirectView redirectView = new RedirectView();
        redirectView.setExposeModelAttributes(true);
        redirectView.setContextRelative(true);
        redirectView.setUrl(uri);

        ModelAndView mav = new ModelAndView();
        mav.setView(redirectView);

        return mav;
    }
}
