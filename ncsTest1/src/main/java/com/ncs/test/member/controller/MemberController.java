package com.ncs.test.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.test.member.model.service.MemberService;
import com.ncs.test.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})

public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// loginMember 조회
	@PostMapping("login")
	public String memberLogin(Member member, Model model, RedirectAttributes ra) {
		
		Member loginMember = service.memberLogin(member);
		
		System.out.println(loginMember);
		
		String path = "redirect:";
		
		if(loginMember != null) {
			System.out.println("회원 로그인 성공");
			
			model.addAttribute("loginMember", loginMember);
			
			path += "/";
			
			return path;
			
		} else {
			System.out.println("회원 로그인 실패");
			
			model.addAttribute("msg", "로그인 실패");
			
			ra.addFlashAttribute("message", "로그인 실패");
			
			return "errorPage";
		}
		
	}

}
