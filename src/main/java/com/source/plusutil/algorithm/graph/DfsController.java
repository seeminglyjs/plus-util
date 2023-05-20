package com.source.plusutil.algorithm.graph;

import com.source.plusutil.algorithm.dto.DfsRequestDto;
import com.source.plusutil.algorithm.dto.DfsResponseDto;
import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plus/algorithm")
@RequiredArgsConstructor
public class DfsController {
    private final DfsServiceImpl dfsService;
    @PostMapping("/dfs/distance")
    @ResponseBody
    public DfsResponseDto bfsDefault(@RequestBody DfsRequestDto dfsRequestDto) {
        return dfsService.dfsDefault(dfsRequestDto);
    }
}
