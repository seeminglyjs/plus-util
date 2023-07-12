package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.entity.UtilLikesDto;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    List<UtilInfoDto> getUtilInfoList(String utilName);
    Optional<UtilInfoDto> getUtilInfoDetail(long utilNo);
    List<UtilInfoDto> getUtilTopList();
    UtilInfoDto getUtilInfoDetailByUrlPath(String urlPath);
    UtilLikeResponseDto addUtilLike(HttpServletRequest request, UtilLikeRequestDto utilLikeRequestDto);
    UtilLikeResponseDto getUtilLikeResponseDto(UtilInfoDto utilInfoDto);
    UtilLikesDto getLikeUtilInfo(HttpServletRequest request);
}
