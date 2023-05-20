package com.source.plusutil.algorithm.graph;

import com.source.plusutil.algorithm.dto.DfsRequestDto;
import com.source.plusutil.algorithm.dto.DfsResponseDto;
import com.source.plusutil.utils.algorithm.DfsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class DfsServiceImpl implements DfsService {

    @Override
    public DfsResponseDto dfsDefault(DfsRequestDto dfsRequestDto) {
        //입력 값들은 15을 넘을 수 없음
        try {
            if (dfsRequestDto.getDfsRow() > 15 || dfsRequestDto.getDfsCol() > 15 |
                    dfsRequestDto.getDfsStartRow() > 14 | dfsRequestDto.getDfsStartCol() > 14 |
                    dfsRequestDto.getDfsEndRow() > 15 | dfsRequestDto.getDfsEndCol() > 15) {
                return DfsResponseDto.builder()
                        .dfsSearchResult(-1)
                        .build();
            } else if (dfsRequestDto.getDfsRow() <  dfsRequestDto.getDfsEndRow() ||  dfsRequestDto.getDfsEndRow() < dfsRequestDto.getDfsStartRow()) {
                return DfsResponseDto.builder()
                        .dfsSearchResult(-1)
                        .build();
            } else if (dfsRequestDto.getDfsCol() < dfsRequestDto.getDfsEndCol() || dfsRequestDto.getDfsEndCol() < dfsRequestDto.getDfsStartCol()) {
                return DfsResponseDto.builder()
                        .dfsSearchResult(-1)
                        .build();
            }
        } catch (Exception e) {
            log.info("exception", e);
            return DfsResponseDto.builder()
                    .dfsSearchResult(-1)
                    .build();
        }
        DfsUtil dfsUtil = new DfsUtil();
        return DfsResponseDto.builder()
                .dfsRow(dfsRequestDto.getDfsRow())
                .dfsCol(dfsRequestDto.getDfsCol())
                .dfsStartRow(dfsRequestDto.getDfsStartRow())
                .dfsStartCol(dfsRequestDto.getDfsStartCol())
                .dfsEndRow(dfsRequestDto.getDfsEndRow())
                .dfsEndCol(dfsRequestDto.getDfsEndCol())
                .dfsSearchResult(dfsUtil.dfsDefaultResult(dfsRequestDto.getDfsRow(), dfsRequestDto.getDfsCol(), dfsRequestDto.getDfsStartRow(), dfsRequestDto.getDfsStartCol(), dfsRequestDto.getDfsEndRow(), dfsRequestDto.getDfsEndCol()))
                .build();
    }
}
