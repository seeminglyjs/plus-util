package com.source.plusutil.controller.algorithmController.graph;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.service.algorithmService.BfsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/algorithm")
@RequiredArgsConstructor
public class BfsController {

	
	private final BfsService bfsService;
	
	@RequestMapping("/bfs/search/short/distance/main")
	public String bfsdefaultMain() {
		return "/algorithm/bfs/bfsMain";
	}
	
	
	@RequestMapping("/bfs/search/short/distance")
	public String bfsdefault(
			@RequestParam(defaultValue = "0") int bfsrow
			, @RequestParam(defaultValue = "0") int bfscol
			, @RequestParam(defaultValue = "0") int bfsStartRow
			, @RequestParam(defaultValue = "0") int bfsStartCol
			, @RequestParam(defaultValue = "0") int bfsEndRow
			, @RequestParam(defaultValue = "0") int bfsEndCol
			, HttpServletRequest request) {
		
		bfsService.bfsDefault(bfsrow, bfscol, bfsStartRow, bfsStartCol, bfsEndRow, bfsEndCol, request);
		
		return "/algorithm/bfs/bfsMain";
	}
	
}
