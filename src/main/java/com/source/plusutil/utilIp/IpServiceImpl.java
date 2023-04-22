package com.source.plusutil.utilIp;

import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@Slf4j
public class IpServiceImpl implements IpService {
    @Override
    public void myIpCheck(HttpServletRequest request) {
        Map<String,String> myIpMap = HttpParamCheckUtil.localeCheck(request);
        if (myIpMap != null) {
            request.setAttribute("ip", myIpMap.get("ip"));
            request.setAttribute("country", myIpMap.get("country"));
        } else {
            request.setAttribute("ip", "확인되지 않음");
            request.setAttribute("country", "확인되지 않음");
        }
    }
}
