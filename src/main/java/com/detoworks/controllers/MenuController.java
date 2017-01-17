package com.detoworks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Banach on 2017-01-15.
 */
@Controller
public class MenuController {

    @RequestMapping("")
    public String homePage(Model model) {
        return "welcome";
    }

    @RequestMapping("/welcome")
    public String welcomePage(Model model) {
        return "redirect:";
    }

    @RequestMapping("/regexp")
    public String regExpPage(Model model) {
        return "regexp";
    }


}
