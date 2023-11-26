package com.cg.controller;


import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public String showHomePage() {

        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/shop")
    public String showShoppingPage(Model model) {
        String username = appUtils.getPrincipalUsername();

        model.addAttribute("username", username);

        return "shop/index";
    }
}
