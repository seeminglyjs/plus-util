package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface UtilInfoService {

    UtilViewResponseDto clickUtilInfo(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto);
    UtilInfoInsertResponseDto enrollUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
    UtilInfoGetResponseDto getUtilInfoList(String utilName);
}
