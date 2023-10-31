package com.kh.test.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.user.model.service.UserService;
import com.kh.test.user.model.vo.User;

@Controller
@RequestMapping("/select")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/UserInfo")
	public String selectUserInfo(String id, Model model) {
		
		User user = service.selectUserInfo(id);
		
		System.out.println(user);
		
		model.addAttribute("user", user);
		
		String path = "redirect:";
		
		if(user != null) { // 입력한 아이디와 일치하는 회원정보가 있을 경우
			path += "searchSuccess"; 
			
		} else { // 없을 경우
			path += "searchFail";
			
		}
		
		return path;
	}

}
