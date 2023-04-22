package com.source.plusutil.algorithm;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.algorithm.BfsServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/plus/algorithm")
@RequiredArgsConstructor
public class BfsController {

	
	private final BfsServiceImpl bfsService;
	
	@RequestMapping("/bfs/search/short/distance/main")
	public String bfsDefaultMain() {
		return AlgorithmReturnUrl.BFS_MAIN.getUrl();
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
		
		return AlgorithmReturnUrl.BFS_MAIN.getUrl();
	}
	
}