package com.escl.citi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/promo")
    public String promoQrCode(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent").toLowerCase();

        boolean android = userAgent.matches(".*android.*");

        if (android) {
            return "redirect:https://play.google.com/store/apps/details?id=puls.escl.puls";
        } else {
            return "redirect:https://apps.apple.com/pl/app/puls-rynku-fx/id1120027460";
        }
    }
}
