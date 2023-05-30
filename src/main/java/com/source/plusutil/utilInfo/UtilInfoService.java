package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertResponseDto;
import com.source.plusutil.utilInfo.dto.UtilViewRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface UtilInfoService {

    UtilViewResponseDto clickUtilInfo(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto);
    UtilInfoInsertResponseDto enrollUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
}
