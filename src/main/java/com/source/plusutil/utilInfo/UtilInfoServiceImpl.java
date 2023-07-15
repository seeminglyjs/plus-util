package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.entity.UtilLikesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UtilInfoServiceImpl implements UtilInfoService {
    private final UtilInfoSimpleService utilInfoSimpleService;

    /*
        유틸 조회에 따른 조회수를 증가시킨다.
     */
    @Override
    public UtilViewResponseDto clickUtilInfo(HttpServletRequest request, UtilViewRequestDto utilViewRequestDto) {
        if (utilViewRequestDto != null) {
            UtilViewResponseDto utilViewResponseDto = utilInfoSimpleService.addUtilView(request, utilViewRequestDto);
            if (utilViewResponseDto == null) {
                return UtilViewResponseDto.builder()
                        .utilNo(-1L)
                        .build();
            } else {
                return utilViewResponseDto;
            }
        }
        return UtilViewResponseDto.builder()
                .utilNo(-1L)
                .build();
    }

    /*
        관리자가 설정한 유틸리티 정보를 확인하여 등록한다.
     */
    @Override
    public UtilInfoInsertResponseDto enrollUtilInfo(UtilInfoInsertRequestDto utilInfoInsertRequestDto) {
        if (utilInfoInsertRequestDto != null) {
            UtilInfoDto utilInfoDto = utilInfoSimpleService.addUtilInfo(utilInfoInsertRequestDto);
            if(utilInfoDto != null){ //유틸리티 정보가 등록이 완료되면
                return UtilInfoInsertResponseDto.builder()
                        .auth(true)
                        .utilName(utilInfoDto.getUtilName())
                        .utilDescription(utilInfoDto.getUtilDescription())
                        .utilViews(utilInfoDto.getUtilViews())
                        .utilLikes(utilInfoDto.getUtilLikes())
                        .urlPath(utilInfoDto.getUrlPath())
                        .category(utilInfoDto.getCategory())
                        .subject(utilInfoDto.getSubject())
                        .build();
            }else{ //유틸리티 등록 실패
                return UtilInfoInsertResponseDto.builder().auth(false).build();
            }
        }else{//요청 정보 확인 불가
            return UtilInfoInsertResponseDto.builder().auth(false).build();
        }
    }

    @Override
    public UtilInfoGetResponseDto getUtilInfoList(String utilName) {
        if (utilName != null) {
            List<UtilInfoDto> utilInfoDtoList = utilInfoSimpleService.getUtilInfoList(utilName);
            if(utilInfoDtoList == null || utilInfoDtoList.size() == 0){ //만약 저장된 정보가 없다면
                return UtilInfoGetResponseDto.builder()
                        .isEmpty(true)
                        .build();
            }else{
                return UtilInfoGetResponseDto.builder()
                        .utilInfoDtoList(utilInfoDtoList)
                        .isEmpty(false)
                        .build();
            }
        }else{//요청 객체 에러
            return UtilInfoGetResponseDto.builder()
                    .isEmpty(true)
                    .build();
        }
    }

    @Override
    public UtilInfoDto getUtilInfoDetail(long utilNo) {
        Optional<UtilInfoDto> utilInfoDtoOp = utilInfoSimpleService.getUtilInfoDetail(utilNo);
        return utilInfoDtoOp.orElse(null);
    }

    @Override
    public UtilInfoGetResponseDto getUtilTopList() {
        List<UtilInfoDto> utilInfoTopDtoList = utilInfoSimpleService.getUtilTopList();
        if(utilInfoTopDtoList == null || utilInfoTopDtoList.isEmpty()) return UtilInfoGetResponseDto.builder().isEmpty(true).build();
        else return UtilInfoGetResponseDto.builder().utilInfoDtoList(utilInfoTopDtoList).isEmpty(false).build();
    }

    @Override
    public UtilPagePropsDto getUtilInfoDetailByUrlPath(String urlPath) {
        UtilInfoDto utilInfoDto = utilInfoSimpleService.getUtilInfoDetailByUrlPath(urlPath);
        System.out.println(utilInfoDto);
        if(utilInfoDto != null){
            return UtilPagePropsDto.builder()
                    .data(utilInfoDto)
                    .build();
        }else{
            return UtilPagePropsDto.builder()
                    .data(null)
                    .build();
        }
    }

    @Override
    public UtilLikeResponseDto likeUtilInfo(HttpServletRequest request,  UtilLikeRequestDto utilLikeRequestDto) {
        if (utilLikeRequestDto != null) {
            UtilLikeResponseDto utilLikeResponseDto = utilInfoSimpleService.addUtilLike(request, utilLikeRequestDto);
            if (utilLikeResponseDto == null) {
                return UtilLikeResponseDto.builder()
                        .utilNo(-1L)
                        .like(false)
                        .build();
            } else {
                return utilLikeResponseDto;
            }
        }
        return UtilLikeResponseDto.builder()
                .utilNo(-1L)
                .like(false)
                .build();
    }

    @Override
    public UtilLikeCheckResponseDto checkLikeUtilInfo(HttpServletRequest request) {
        UtilLikesDto utilLikesDto =  utilInfoSimpleService.getLikeUtilInfo(request);
        if(utilLikesDto != null) {
            return UtilLikeCheckResponseDto.builder()
                    .like(true)
                    .ip(utilLikesDto.getLikeIp())
                    .build();
        }else{
            return UtilLikeCheckResponseDto.builder()
                    .like(false)
                    .ip("unknown")
                    .build();
        }
    }

    @Override
    public UtilLikeRevokeResponseDto revokeLikeUtilInfo(HttpServletRequest request, UtilLikeRevokeRequestDto utilLikeRevokeRequestDto) {
        return  utilInfoSimpleService.revokeLikeUtilInfo(request,utilLikeRevokeRequestDto);
    }
}
