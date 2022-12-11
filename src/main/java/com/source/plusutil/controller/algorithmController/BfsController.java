package com.source.plusutil.controller.algorithmController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.source.plusutil.service.algorithmService.BfsService;

@Controller
@RequestMapping("/algorithm")
public class BfsController {

	@Autowired
	BfsService bfsService;
	
	@RequestMapping("/bfs/search/short/distance/main")
	public String bfsdefaultMain() {
		return "/algorithm/bfs/bfsMain";
	}
	
	
	@RequestMapping("/bfs/search/short/distance")
	public String bfsdefault(
			@RequestParam int row
			, @RequestParam int col
			, @RequestParam int startRow
			, @RequestParam int startCol
			, @RequestParam int endRow
			, @RequestParam int endCol
			, @RequestParam int block) {
		
		return "/algorithm/bfs/bfsMain";
	}
	
}
