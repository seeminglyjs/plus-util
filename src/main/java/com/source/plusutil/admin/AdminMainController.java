package com.source.plusutil.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plus")
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
