package com.source.plusutil.utilInfo;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertResponseDto;
import com.source.plusutil.utilInfo.dto.UtilViewRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewResponseDto;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plus/util/info")
@RequiredArgsConstructor
public class UtilInfoController {

    private final UtilInfoService utilInfoService;

    @PutMapping("/view/click")
    @ResponseBody
    public UtilViewResponseDto clickUtilInfo(HttpServletRequest request, @RequestBody UtilViewRequestDto utilViewRequestDto){
        return utilInfoService.clickUtilInfo(request,utilViewRequestDto);
    }

    @PostMapping("/enroll")
    @ResponseBody
    public UtilInfoInsertResponseDto enrollUtilInfo(@RequestBody UtilInfoInsertRequestDto utilInfoInsertRequestDto){
          if (AuthObjectUtil.authenticationConfirm(SecurityContextHolder.getContext().getAuthentication(), UserRolePlusEnum.ROLE_ADMIN.toString())) { //권한 체크
              return utilInfoService.enrollUtilInfo(utilInfoInsertRequestDto);
        } else {
            return UtilInfoInsertResponseDto.builder()
                    .auth(false)
                    .build();
        }
    }
}
