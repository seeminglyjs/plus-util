package com.source.plusutil.service.utilService.etcUtil;

import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.javainetlocator.InetAddressLocatorException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class IpServiceImpl implements IpService {
    @Override
    public void myIpCheck(HttpServletRequest request) {
        Map<String,String> myIpMap = new HashMap<>();
        try {
            myIpMap = HttpParamCheckUtil.getHttpRequestIpInfo(request);
            if (myIpMap != null) {
                request.setAttribute("ip", myIpMap.get("ip"));
                request.setAttribute("country", myIpMap.get("country"));
            }else{
                request.setAttribute("ip", "확인되지 않음");
                request.setAttribute("country", "확인되지 않음");
            }
        } catch (InetAddressLocatorException e) {
            request.setAttribute("ip", "오류발생");
            request.setAttribute("country", "오류발생");
        }
    }
}
