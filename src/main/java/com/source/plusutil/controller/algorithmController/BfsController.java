package com.source.plusutil.controller.algorithmController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.source.plusutil.service.algorithmService.BfsService;

@Controller
public class BfsController {

	@Autowired
	BfsService bfsService;
	
}
