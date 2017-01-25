package com.detoworks.controllers;

import com.detoworks.dto.RegExpDto;
import com.detoworks.model.RegExp;
import com.detoworks.model.RegExpKey;
import com.detoworks.service.RegExpService;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Banach on 2017-01-15.
 */
@Controller
public class RegExpController {

    @Autowired
    private RegExpService regExpService;


    @RequestMapping(value = "/regexp", method = RequestMethod.GET)
    public String regExpPage(Model model, @ModelAttribute RegExpDto regExpDto) {
        model.addAttribute("regExpDto", regExpDto);
        return "regexp";
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.GET)
    public String regExpKeyPage(@PathVariable long id, @PathVariable int subid, Model model, @ModelAttribute RegExpDto regExpDto) {
        RegExp regExp = regExpService.findOne(new RegExpKey(id, subid));
        if (null == regExp) {
            return "redirect:/regexp";
        }
        model.addAttribute("regExpDto", regExpDto.setFromModel(regExp));
        return "regexp";
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST, params = { "calc" })
    public String regExpCalc(HttpServletRequest request, RedirectAttributes redirectAttributes,
                             @RequestParam long id, @RequestParam int subid, Model model, @ModelAttribute RegExpDto regExpDto) {
        calculate(regExpDto);
        redirectAttributes.addFlashAttribute("regExpDto", regExpDto);
        return "redirect:/regexp";
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST, params = { "calcAndSave" })
    public String regExpCalcAndSave(HttpServletRequest request, RedirectAttributes redirectAttributes,
                               @RequestParam long id, @RequestParam int subid, Model model, @ModelAttribute RegExpDto regExpDto) {
        RegExp regExp = new RegExp(regExpDto);
        regExpService.save(regExp);
        calculate(regExpDto);
        redirectAttributes.addFlashAttribute("regExpDto", regExpDto.setFromModel(regExp));
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST, params = { "calcAndVersion" })
    public String regExpCalcAndVersion(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                    @RequestParam long id, @RequestParam int subid, Model model, @ModelAttribute RegExpDto regExpDto) {

        //TODO - check if something has changed - if nothing then error

        regExpDto.setSubid(0);
        RegExp regExp = new RegExp(regExpDto);
        regExpService.save(regExp);
        calculate(regExpDto);
        redirectAttributes.addFlashAttribute("regExpDto", regExpDto.setFromModel(regExp));
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

    @RequestMapping(value = "/regexp", method = RequestMethod.POST, params = { "calcAndFork" })
    public String regExpCalcAndFork(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                    @RequestParam long id, @RequestParam int subid, Model model, @ModelAttribute RegExpDto regExpDto) {
        regExpDto.setId(0);
        regExpDto.setSubid(0);
        RegExp regExp = new RegExp(regExpDto);
        regExpService.save(regExp);
        calculate(regExpDto);
        redirectAttributes.addFlashAttribute("regExpDto", regExpDto.setFromModel(regExp));
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

    @RequestMapping(value = "/regexp/{id}/{subid}", method = RequestMethod.POST)
    public String regExpKeySubmit(HttpServletRequest request, RedirectAttributes redirectAttributes,
                                  @PathVariable long id, @PathVariable int subid, @ModelAttribute RegExpDto regExpDto) {
        regExpDto.setId(id);
        regExpDto.setSubid(subid);
        RegExp regExp = new RegExp(regExpDto);
        regExpService.save(regExp);
        redirectAttributes.addFlashAttribute("regExpDto", regExpDto.setFromModel(regExp));
        return "redirect:/regexp/"+ regExpDto.getId()+"/"+ regExpDto.getSubid();
    }

    private void calculate(RegExpDto regExpDto) {

        String output = "";
        ArrayList<String> tree = new ArrayList<String>();;
        try {
            if (regExpDto.getInput().equals("") & regExpDto.getFindexp().equals("")) {
                regExpDto.setOutputTree(output);
                regExpDto.setOutput(output);
                return;
            }

            java.util.regex.Pattern pattern = java.util.regex.Pattern
                    .compile(regExpDto.getFindexp());
            java.util.regex.Matcher matcher = pattern.matcher(regExpDto.getInput());
            java.lang.StringBuffer sb = null;

            int counter = 1;
            while (matcher.find()) {
                if (regExpDto.getReplexp() != "") {
                    sb = new java.lang.StringBuffer();
                    matcher.appendReplacement(sb, regExpDto.getReplexp());
                    output += sb.toString();
                } else {
                    output += (matcher.group() + "\n");
                }

                String entry = "Match " + counter + ": '" + matcher.group()
                        + "' (start at: " + matcher.start() + ", end at: "
                        + (matcher.end() - 1) + ", len: "
                        + (matcher.end() - matcher.start()) + ")";
                tree.add(calcTreeEntry(entry, 0));
                for (int i = 1; i <= matcher.groupCount(); ++i) {
                    entry = "Group ($" + i + "): '" + matcher.group(i) + "'";
                    tree.add(calcTreeEntry(entry, 1));
                    entry = "Start at: " + matcher.start(i) + ", end at: "
                            + (matcher.end(i) - 1) + ", len: "
                            + (matcher.end(i) - matcher.start(i));
                    tree.add(calcTreeEntry(entry, 2));
                }
                ++counter;
            }
            if (regExpDto.getReplexp() != "") {
                sb = new java.lang.StringBuffer();
                matcher.appendTail(sb);
                output += sb.toString();
            }
        } catch (Exception e) {
            tree.add(e.getMessage());
        }
        regExpDto.setOutputTree(StringUtils.join(tree, "\n"));
        regExpDto.setOutput(output);
    }

    private String calcTreeEntry(String entry_txt, int lvl) {
        String entry = entry_txt;
        if (lvl > 0) {
            String prefix = StringUtils.repeat("     ", lvl);
            entry = prefix + "- " + entry;
        }
        return entry;
    }
}
