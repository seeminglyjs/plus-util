package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

public interface UtilInfoSimpleService {
    Page<UtilInfoDto> getUtilList(int limit);
    UtilInfoDto getUtilInfoById(long id);
    UtilInfoDto getUtilInfoByName(String name);
    UtilInfoDto addUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto);
    List<UtilInfoInsertRequestDto> addUtilInfoList(List<UtilInfoInsertRequestDto> utilInfoInsertRequestDtoList);
    UtilInfoDto modifyUtilInfo(UtilInfoUpdateRequestDto utilInfoUpdateRequest);
    List<UtilInfoUpdateRequestDto> modifyUtilInfoList(List<UtilInfoUpdateRequestDto> utilInfoUpdateRequestList);
    long removeUtilInfo(UtilInfoDeleteRequestDto utilInfoDeleteRequestDto);
    long removeUtilInfoList(List<UtilInfoDeleteRequestDto> utilInfoDeleteRequestDtoList);
    UtilViewResponseDto addUtilView(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto);
    UtilViewResponseDto getUtilViewResponseDto(UtilInfoDto utilInfoDto);
    List<UtilInfoDto> getUtilInfoList(UtilInfoGetRequestDto utilInfoGetRequestDto);
}
