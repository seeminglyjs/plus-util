package com.source.plusutil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/error/main")
    public String errorMain(){
        return "/error/errorMain.html";
    }
}
