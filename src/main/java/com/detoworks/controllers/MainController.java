package com.detoworks.controllers;

import com.detoworks.dto.RegExpDto;
import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.repository.RegExpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
        model.addAttribute("regExpDto", new RegExpDto());
        return "regexp";
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.GET)
    public String regExpKeyPage(@PathVariable long id, @PathVariable int subid, Model model) {
        RegExp regExp = dao.findOne(new RegExpKey(id, subid));
        if (null == regExp) {
            return "redirect:/regexp";
        }
        model.addAttribute("regExpDto", new RegExpDto(regExp));
        return "regexp";
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST)
    public String regExpSubmit(HttpServletRequest request, RedirectAttributes redirectAttributes,
                               @ModelAttribute RegExpDto regExpDto) {
        dao.save(new RegExp(regExpDto));
        redirectAttributes.addAttribute("regExpDto", regExpDto);
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.POST)
    public String regExpKeySubmit(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                  @ModelAttribute RegExpDto regExpDto) {
        dao.save(new RegExp(regExpDto));
        redirectAttributes.addAttribute("regExpDto", regExpDto);
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

}
