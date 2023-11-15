package edu.kh.fin.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.fin.member.model.dto.Member;
import edu.kh.fin.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@PostMapping("/login")
	public String login(Member member, Model model, RedirectAttributes ra) {
		
		Member loginMember = service.login(member);
		
		System.out.println(loginMember);
		
		String path = "common/";
		
		if(loginMember != null) {
			path += "loginSuccess";
			
			model.addAttribute("loginMember", loginMember);		
			
		} else {
			path += "loginFail";
			
			ra.addFlashAttribute("message", "아이디 또는 비밀번호가 불일치합니다.");
		}
		
		return path;
	}
	
	@GetMapping("/selectMemberTel")
	@ResponseBody
	public String selectMemberTel(String nickname) {
		
		String memberTel = service.selectMemberTel(nickname);
		
		System.out.println(memberTel);
		
		return memberTel;
	}

}
