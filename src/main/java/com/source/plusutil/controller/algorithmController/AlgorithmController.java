package com.source.plusutil.controller.algorithmController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/algorithm")
public class AlgorithmController {

	@GetMapping("/graph/main")
	public String algorithmGraphMain() {
		return "/algorithm/graph/graphMain";
	}
	
}
