package com.source.plusutil.algorithm;

import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plus/algorithm")
public class AlgorithmController {

	@GetMapping("/graph/main")
	public String algorithmGraphMain() {
		return AlgorithmReturnUrl.GRAPH_MAIN.getUrl();
	}

	@GetMapping("/basic/main")
	public String algorithmBasicMain(){return AlgorithmReturnUrl.BASIC_MAIN.getUrl();}
}
