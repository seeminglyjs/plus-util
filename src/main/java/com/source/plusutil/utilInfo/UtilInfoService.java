package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;

import javax.servlet.http.HttpServletRequest;

public interface UtilInfoService {

    UtilViewResponseDto clickUtilInfo(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto);
    UtilInfoInsertResponseDto enrollUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
    UtilInfoGetResponseDto getUtilInfoList(String utilName);
    UtilInfoDto getUtilInfoDetail(long utilNo);
    UtilInfoGetResponseDto getUtilTopList();
    UtilPagePropsDto getUtilInfoDetailByUrlPath(String urlPath);
    UtilLikeResponseDto likeUtilInfo(HttpServletRequest request,  UtilLikeRequestDto utilLikeRequestDto);
    UtilLikeCheckResponseDto checkLikeUtilInfo(HttpServletRequest request, long utilNo);
    UtilLikeRevokeResponseDto revokeLikeUtilInfo(HttpServletRequest request, UtilLikeRevokeRequestDto utilLikeRevokeRequestDto);
}
