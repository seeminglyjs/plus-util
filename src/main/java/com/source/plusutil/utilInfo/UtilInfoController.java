package com.source.plusutil.utilInfo;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plus/util/info")
@RequiredArgsConstructor
public class UtilInfoController {

    private final UtilInfoService utilInfoService;

    @PutMapping("/view/click")
    @ResponseBody
    public UtilViewResponseDto clickUtilInfo(HttpServletRequest request, @RequestBody UtilViewRequestDto utilViewRequestDto) {
        return utilInfoService.clickUtilInfo(request, utilViewRequestDto);
    }

    @PostMapping("/enroll")
    @ResponseBody
    public UtilInfoInsertResponseDto enrollUtilInfo(@RequestBody UtilInfoInsertRequestDto utilInfoInsertRequestDto) {
        if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return utilInfoService.enrollUtilInfo(utilInfoInsertRequestDto);
        } else {
            return UtilInfoInsertResponseDto.builder()
                    .auth(false)
                    .build();
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public UtilInfoGetResponseDto getUtilInfoList(String utilName) {
        if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
            return utilInfoService.getUtilInfoList(utilName);
        } else {
            return UtilInfoGetResponseDto.builder()
                    .isEmpty(true)
                    .build();
        }
    }
    @GetMapping("/detail")
    @ResponseBody
    public UtilInfoDto getUtilInfoDetail(long utilNo){
        return null;
    }

}
