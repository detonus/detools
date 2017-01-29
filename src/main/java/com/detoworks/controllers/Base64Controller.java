package com.detoworks.controllers;

import com.detoworks.dto.Base64Dto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Base64;

/**
 * Created by Banach on 2017-01-29.
 */
@Controller
public class Base64Controller {

    @RequestMapping("/base64")
    public String base64Page(Model model, @ModelAttribute Base64Dto base64Dto) {
        model.addAttribute("base64Dto", base64Dto);
        return "base64";
    }

    @RequestMapping(value = "/base64", method = RequestMethod.POST, params = { "encode" })
    public String base64Encode(Model model, @ModelAttribute Base64Dto base64Dto) {
        encode(base64Dto);
        model.addAttribute("base64Dto", base64Dto);
        return "base64";
    }

    @RequestMapping(value = "/base64", method = RequestMethod.POST, params = { "decode" })
    public String base64Decode(Model model, @ModelAttribute Base64Dto base64Dto) {
        decode(base64Dto);
        model.addAttribute("base64Dto", base64Dto);
        return "base64";
    }

    private void encode(Base64Dto base64Dto) {
        base64Dto.setEncoded(new String(Base64.getEncoder().encode(base64Dto.getDecoded().getBytes())));
    }

    private void decode(Base64Dto base64Dto) {
        base64Dto.setDecoded(new String(Base64.getDecoder().decode(base64Dto.getEncoded().getBytes())));
    }

}
