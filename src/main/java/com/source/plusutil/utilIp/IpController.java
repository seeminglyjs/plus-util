package com.source.plusutil.utilIp;

import com.source.plusutil.utilIp.IpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/plus/util/etc")
@RequiredArgsConstructor
public class IpController {

    private final IpServiceImpl ipService;

    @GetMapping("/ip/my/check/main")
    public String myIpCheckMain(HttpServletRequest request){
        ipService.myIpCheck(request);
        return "/util/etc/ip/myIpCheckMain.html";
    }
}