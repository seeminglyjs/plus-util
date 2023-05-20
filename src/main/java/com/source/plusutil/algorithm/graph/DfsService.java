package com.source.plusutil.algorithm.graph;

import com.source.plusutil.algorithm.dto.DfsRequestDto;
import com.source.plusutil.algorithm.dto.DfsResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface DfsService {
    DfsResponseDto dfsDefault(DfsRequestDto dfsRequestDto);
}
