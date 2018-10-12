package com.inShowAdmin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inShowAdmin.pojo.Admin;
import com.inShowAdmin.utils.JSONResult;
@RestController
public class loginController {
	@RequestMapping("/adminLogin")
	public JSONResult login(String username,String password) {
		Admin admin = new Admin();
		if(admin.getName().equals(username)&&admin.getPassword().equals(password)) {
			return JSONResult.ok();
		}
		return JSONResult.errorMsg("对不起，请重新登陆");
	}
}
