package com.source.plusutil.utilInfo;

import com.source.plusutil.admin.dto.AdminRoleResponseDto;
import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plus/util/info")
@RequiredArgsConstructor
@Slf4j
public class UtilInfoController {

    private final UtilInfoService utilInfoService;

    @PutMapping("/view/click")
    @ResponseBody
    public UtilViewResponseDto clickUtilInfo(HttpServletRequest request, @RequestBody UtilViewRequestDto utilViewRequestDto) {
        return utilInfoService.clickUtilInfo(request, utilViewRequestDto);
    }

    @PostMapping("/enroll")
    @ResponseBody
    public AdminRoleResponseDto enrollUtilInfo(@RequestBody UtilInfoInsertRequestDto utilInfoInsertRequestDto) {
        return AdminRoleResponseDto.builder()
                .auth(true)
                .dto(utilInfoService.enrollUtilInfo(utilInfoInsertRequestDto))
                .build();
    }

    @GetMapping("/list")
    @ResponseBody
    public AdminRoleResponseDto getUtilInfoList(String utilName) {
        return AdminRoleResponseDto.builder()
                .auth(true)
                .dto(utilInfoService.getUtilInfoList(utilName))
                .build();
    }

    @GetMapping("/top/list")
    @ResponseBody
    public UtilInfoGetResponseDto getUtilTopList(){
        return utilInfoService.getUtilTopList();
    }

    @GetMapping("/detail")
    @ResponseBody
    public AdminRoleResponseDto getUtilInfoDetail(long utilNo) {
        UtilInfoDto utilInfoDto = utilInfoService.getUtilInfoDetail(utilNo);
        if (utilInfoDto == null) {
            return AdminRoleResponseDto.builder()
                    .auth(false)
                    .dto(null)
                    .build();
        } else {
            return AdminRoleResponseDto.builder()
                    .auth(true)
                    .dto(utilInfoDto)
                    .build();
        }
    }
}
