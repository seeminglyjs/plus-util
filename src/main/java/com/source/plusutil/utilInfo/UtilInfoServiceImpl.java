package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilViewRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
@RequiredArgsConstructor
public class UtilInfoServiceImpl implements UtilInfoService {
    private final UtilInfoSimpleService utilInfoSimpleService;

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
}
