package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDeleteRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoUpdateRequestDto;

import java.util.List;

public interface UtilInfoService {
    List<UtilInfoDto> getUtilList(int limit);
    UtilInfoDto findUtilInfoById(long id);
    UtilInfoDto findUtilInfoByName(String name);
    long insertUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
    long insertUtilInfoList(List<UtilInfoInsertRequestDto> utilInfoInsertRequestDtoList);
    long updateUtilInfo(UtilInfoUpdateRequestDto utilInfoUpdateRequest);
    long updateUtilInfoList(List<UtilInfoUpdateRequestDto> utilInfoUpdateRequestList);
    long deleteUtilInfo(UtilInfoDeleteRequestDto utilInfoDeleteRequestDto);
    long deleteUtilInfoList(List<UtilInfoDeleteRequestDto> utilInfoDeleteRequestDtoList);
}
