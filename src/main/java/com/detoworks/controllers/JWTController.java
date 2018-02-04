package com.detoworks.controllers;

import com.detoworks.dto.Base64Dto;
import com.detoworks.dto.JWTDto;
import com.detoworks.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JWTController {

    @Autowired
    private JWTService jwtService;

    @RequestMapping("/jwt")
    public String base64Page(Model model, @ModelAttribute JWTDto jwtDto) {
        model.addAttribute("jwtDto", jwtDto);
        return "jwt";
    }

    @RequestMapping(value = "/jwt", method = RequestMethod.POST, params = { "decode" })
    public String base64Decode(Model model, @ModelAttribute JWTDto jwtDto) {
        decode(jwtDto);
        model.addAttribute("jwtDto", jwtDto);
        return "jwt";
    }

    private void decode(JWTDto jwtDto) {
        jwtDto.setDecoded(jwtService.decoded(jwtDto.getToken()));
    }

}
