package com.detoworks.controllers;

import com.detoworks.model.Fiddle;
import com.detoworks.model.FiddleKey;
import com.detoworks.repository.RegExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Banach on 2017-01-15.
 */
@Controller
public class MainController {

    @Autowired
    private RegExpDao dao;

    @RequestMapping("")
    public String homePage(Model model) {
        return "welcome";
    }

    @RequestMapping("/welcome")
    public String welcomePage(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.GET)
    public String regExpPage(Model model) {
        model.addAttribute("fiddle", new Fiddle());
        return "regexp";
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.GET)
    public String regExpKeyPage(@PathVariable long id, @PathVariable int subid, Model model) {
        Fiddle fiddle = dao.findOne(new FiddleKey(id, subid));
        if (null == fiddle) {
            return "redirect:/regexp";
        }
        model.addAttribute("fiddle", fiddle);
        return "regexp";
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST)
    public String regExpSubmit(@ModelAttribute Fiddle fiddle) {
        dao.save(fiddle);
        return "redirect:/regexp/"+fiddle.getId()+"/"+fiddle.getSubid();
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.POST)
    public String regExpKeySubmit(@ModelAttribute Fiddle fiddle) {
        dao.save(fiddle);
        return "redirect:/regexp/"+fiddle.getId()+"/"+fiddle.getSubid();
    }

}
