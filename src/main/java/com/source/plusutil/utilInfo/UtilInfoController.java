package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilViewRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/util/info")
@RequiredArgsConstructor
public class UtilInfoController {

    private final UtilInfoService utilInfoService;

    @PostMapping("/view/clcik")
    @ResponseBody
    public UtilViewResponseDto clickUtilInfo(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto){
        return utilInfoService.clickUtilInfo(request,utilViewRequestDto);
    }

}
