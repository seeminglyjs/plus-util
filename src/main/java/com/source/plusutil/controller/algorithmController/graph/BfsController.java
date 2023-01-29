package com.source.plusutil.controller.algorithmController.graph;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.service.algorithmService.BfsServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/algorithm")
@RequiredArgsConstructor
public class BfsController {

	
	private final BfsServiceImpl bfsService;
	
	@RequestMapping("/bfs/search/short/distance/main")
	public String bfsDefaultMain() {
		return "/algorithm/bfs/bfsMain";
	}
	
	
	@RequestMapping("/bfs/search/short/distance")
	public String bfsDefault(
			@RequestParam(defaultValue = "0") int bfsRow
			, @RequestParam(defaultValue = "0") int bfsCol
			, @RequestParam(defaultValue = "0") int bfsStartRow
			, @RequestParam(defaultValue = "0") int bfsStartCol
			, @RequestParam(defaultValue = "0") int bfsEndRow
			, @RequestParam(defaultValue = "0") int bfsEndCol
			, HttpServletRequest request) {
		
		bfsService.bfsDefault(bfsRow, bfsCol, bfsStartRow, bfsStartCol, bfsEndRow, bfsEndCol, request);
		
		return "/algorithm/bfs/bfsMain";
	}
	
}
