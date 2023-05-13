package com.source.plusutil.utilIp;

import com.source.plusutil.utilIp.IpServiceImpl;
import com.source.plusutil.utilIp.dto.MyIpCheckResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plus/util/etc/ip")
@RequiredArgsConstructor
public class IpController {

    private final IpServiceImpl ipService;

    @GetMapping("/my")
    @ResponseBody
    public MyIpCheckResponseDto myIpCheckMain(HttpServletRequest request){
        return ipService.myIpCheck(request);
    }
}
