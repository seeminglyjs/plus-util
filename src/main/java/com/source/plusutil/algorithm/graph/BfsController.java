package com.source.plusutil.algorithm.graph;

import javax.servlet.http.HttpServletRequest;

import com.source.plusutil.algorithm.dto.BfsRequestDto;
import com.source.plusutil.algorithm.dto.BfsResponseDto;
import com.source.plusutil.enums.returnUrl.AlgorithmReturnUrl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/plus/algorithm")
@RequiredArgsConstructor
public class BfsController {
	
	private final BfsServiceImpl bfsService;

	@PostMapping("/bfs/distance")
	@ResponseBody
	public BfsResponseDto bfsDefault(@RequestBody BfsRequestDto bfsRequestDto) {
		return bfsService.bfsDefault(bfsRequestDto);
	}
}
