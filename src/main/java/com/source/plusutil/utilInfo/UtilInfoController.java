package com.source.plusutil.utilInfo;

import com.source.plusutil.admin.dto.AdminRoleResponseDto;
import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @PutMapping("/like")
    @ResponseBody
    public UtilLikeResponseDto likeUtilInfo(HttpServletRequest request, @RequestBody UtilLikeRequestDto utilLikeRequestDto) {
        return utilInfoService.likeUtilInfo(request, utilLikeRequestDto);
    }

    @GetMapping("/like/check")
    @ResponseBody
    public UtilLikeCheckResponseDto checkLikeUtilInfo(HttpServletRequest request){
        return utilInfoService.checkLikeUtilInfo(request);
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
    @PostMapping("/detail/url")
    @ResponseBody
    public UtilPagePropsDto getUtilInfoDetail(@RequestBody UtilInfoGetByUrlPathRequestDto utilInfoGetByUrlPathRequestDto) {
        return utilInfoService.getUtilInfoDetailByUrlPath(utilInfoGetByUrlPathRequestDto.getUrlPath());
    }
}
