package com.mukund.mvcsecurity.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
/**
 * Controller class for Home page
 * 
 * @since 1.0
 * @author Mukund Bhardwaj
 */
public class HomeController {

    @GetMapping
    public String home(Principal details, Model model) {
        model.addAttribute("name", details.getName());
        return "home";
    }

}
