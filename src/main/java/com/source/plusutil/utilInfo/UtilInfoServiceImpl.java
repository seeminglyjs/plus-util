package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


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
                        .build();
            }else{ //유틸리티 등록 실패
                return UtilInfoInsertResponseDto.builder().auth(false).build();
            }
        }else{//요청 정보 확인 불가
            return UtilInfoInsertResponseDto.builder().auth(false).build();
        }
    }
}
