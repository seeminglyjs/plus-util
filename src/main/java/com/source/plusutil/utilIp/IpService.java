package com.source.plusutil.utilIp;

import com.source.plusutil.utilIp.dto.MyIpCheckResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface IpService {

    MyIpCheckResponseDto myIpCheck(HttpServletRequest request);
}
