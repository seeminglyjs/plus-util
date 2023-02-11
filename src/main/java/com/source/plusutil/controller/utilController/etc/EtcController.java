package com.source.plusutil.controller.utilController.etc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plus/util")
@RequiredArgsConstructor
public class EtcController {

    @GetMapping("/etc/main")
    public String etcMain(){return "/util/etc/etcMain.html";}
}
