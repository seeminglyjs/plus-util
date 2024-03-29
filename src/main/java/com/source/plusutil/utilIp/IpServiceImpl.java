package com.source.plusutil.utilIp;

import com.source.plusutil.utilIp.dto.MyIpCheckResponseDto;
import com.source.plusutil.utils.http.HttpParamCheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
@Slf4j
public class IpServiceImpl implements IpService {
    @Deprecated
    @Override
    public MyIpCheckResponseDto myIpCheck(HttpServletRequest request) {
        Map<String, String> myIpMap = HttpParamCheckUtil.localeCheck(request);
        if (myIpMap != null) {
            return MyIpCheckResponseDto.builder()
                    .ip(myIpMap.get("ip"))
                    .country(myIpMap.get("country"))
                    .build();
        } else {
            return MyIpCheckResponseDto.builder()
                    .ip("확인되지 않음")
                    .country("확인되지 않음")
                    .build();
        }
    }
}
