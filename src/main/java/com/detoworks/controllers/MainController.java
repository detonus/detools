package com.detoworks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Banach on 2017-01-25.
 */
@Controller
public class MainController {

    @RequestMapping("")
    public String homePage(Model model) {
        return "welcome";
    }

    @RequestMapping("/welcome")
    public String welcomePage(Model model) {
        return "redirect:/";
    }

}
