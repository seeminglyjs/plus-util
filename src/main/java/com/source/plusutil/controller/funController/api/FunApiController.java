package com.source.plusutil.controller.funController.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/fun/api")
public class FunApiController {

    @GetMapping("/main")
    public String funApiMain(){
        return "";
    }

    @GetMapping("/number/info")
    public String getFunNumberInfo(HttpServletRequest request, int number){
        return "";
    }
}
