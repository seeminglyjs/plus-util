package com.source.plusutil.algorithm.graph;

import com.source.plusutil.algorithm.dto.BfsRequestDto;
import com.source.plusutil.algorithm.dto.BfsResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface BfsService {
    BfsResponseDto bfsDefault(BfsRequestDto bfsRequestDto);
    public int bfsArrDefault(int[][] maps, int startRow, int startCol, int endRow, int endCol, int block);
}
