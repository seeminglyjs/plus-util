package com.source.plusutil.controller.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {

	@RequestMapping("/admin/role/test")
	public String adminRoleTest() {
		return "/admin/roleTest";
	}
	
	@RequestMapping("/user/role/test")
	public String userRoleTest() {
		return "/user/roleTest";
	}
}
