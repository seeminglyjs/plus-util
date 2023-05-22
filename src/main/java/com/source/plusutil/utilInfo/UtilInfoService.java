package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDeleteRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoUpdateRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UtilInfoService {
    Page<UtilInfoDto> getUtilList(int limit);
    UtilInfoDto getUtilInfoById(long id);
    UtilInfoDto getUtilInfoByName(String name);
    UtilInfoDto addUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
    List<UtilInfoInsertRequestDto> addUtilInfoList(List<UtilInfoInsertRequestDto> utilInfoInsertRequestDtoList);
    UtilInfoDto modifyUtilInfo(UtilInfoUpdateRequestDto utilInfoUpdateRequest);
    List<UtilInfoUpdateRequestDto> modifyUtilInfoList(List<UtilInfoUpdateRequestDto> utilInfoUpdateRequestList);
    long removeUtilInfo(UtilInfoDeleteRequestDto utilInfoDeleteRequestDto);
    long removeUtilInfoList(List<UtilInfoDeleteRequestDto> utilInfoDeleteRequestDtoList);
}
