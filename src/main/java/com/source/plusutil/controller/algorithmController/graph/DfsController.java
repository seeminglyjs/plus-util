package com.source.plusutil.controller.algorithmController.graph;

import com.source.plusutil.service.algorithmService.DfsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/algorithm")
@RequiredArgsConstructor
public class DfsController {

    private final DfsServiceImpl dfsService;
    @RequestMapping("/dfs/search/short/distance/main")
    public String dfsDefaultMain() {
        return "/algorithm/dfs/dfsMain";
    }


    @RequestMapping("/dfs/search/short/distance")
    public String bfsDefault(
            @RequestParam(defaultValue = "0") int dfsRow
            , @RequestParam(defaultValue = "0") int dfsCol
            , @RequestParam(defaultValue = "0") int dfsStartRow
            , @RequestParam(defaultValue = "0") int dfsStartCol
            , @RequestParam(defaultValue = "0") int dfsEndRow
            , @RequestParam(defaultValue = "0") int dfsEndCol
            , HttpServletRequest request) {

        dfsService.dfsDefault(dfsRow, dfsCol, dfsStartRow, dfsStartCol, dfsEndRow, dfsEndCol, request);

        return "/algorithm/dfs/dfsMain";
    }
}
