package edu.kh.project.myPage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.myPage.MypageService;

@SessionAttributes({"loginMember"})
// 1) Model에 세팅된 값의 key와 {} 작성된 값이 일치하면 session scope로 이동
// 2) Session으로 올려둔 값을 해당 클래스에서 얻어와 사용 가능하게함
//	-> @SessionAttribute(key)로 사용
@RequestMapping("/myPage")
@Controller
public class MyPageController {
	
	@Autowired
	private MypageService service;
	
	// 내 정보 페이지로 이동
	@GetMapping("/info")
	public String info() {
		return "myPage/myPage-info";
	}
	
	// 프로필 페이지 이동
	@GetMapping("/profile")
	public String profile() {
		return "myPage/myPage-profile";
	}
	
	
	// 비밀번호 변경 페이지 이동
	@GetMapping("/changePw")
	public String changePw() {
		return "myPage/myPage-changePw";
	}
	
	// 비밀번호 변경
	@PostMapping("/changePw")
	public String changePwUpdate(@SessionAttribute("loginMember") Member loginMember,
								String newPw,
								RedirectAttributes ra,
								SessionStatus status) {
		
		int result = service.changePwUpdate(loginMember, newPw);
		
		String message = null;
		
		if(result > 0) {
			System.out.println("비밀번호 변경 성공");
			
			message = "비밀번호 변경 성공";
			
		} else {
			System.out.println("비밀번호 변경 실패");
			
			message = "비밀번호 변경 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		status.setComplete();
		
		return "redirect:/";
	}
	
	// 탈퇴 페이지 이동
	@GetMapping("/secession")
	public String secession() {
		return "myPage/myPage-secession";
	}
	
	// 회원 탈퇴
	@PostMapping("/memberDelete")
	public String memberDelete(@SessionAttribute("loginMember") Member loginMember,
								RedirectAttributes ra,
								SessionStatus status) {
		
		int result = service.memberDelete(loginMember);
		
		String message = null;
		
		if(result > 0) {
			System.out.println("회원 탈퇴 성공");
			
			message = "회원 탈퇴 성공";
			
		} else {
			System.out.println("회원 탈퇴 실패");
			
			message = "회원 탈퇴 실패";
			
		}
		
		ra.addFlashAttribute("message", message);
		
		status.setComplete();
	    
		return "redirect:/";
	}
	
	
	// 회원 정보 수정
	@PostMapping("/info")
	public String updateInfo(@SessionAttribute("loginMember") Member loginMember,
							Member updateMember,
							String[] memberAddress,
							RedirectAttributes ra) {
		
		/*
		 * @SessionAttribute("loginMember") Member loginMember
		 * 	: Session에서 얻어온 "loginMember"에 해당하는 객체를
		 * 	  매개변수 Member loginMember에 저장
		 * 
		 * 
		 * Member updateMember
		 * 	: 수정할 닉네임, 전화번호 담긴 커맨드 객체
		 * 
		 * 
		 * String[] memberAddress
		 * 	: name="memberAddress"인 input 3개의 값(주소)
		 * 
		 * 
		 * RedirectAttributes ra : 리다이렉트 시 값 전달용 객체
		 * 
		 * */
		
		// 주소 하나로 합치자 (우편번호^^^주소^^^상세주소)
		if(updateMember.getMemberAddress().equals(",,")) {
			updateMember.setMemberAddress(null);
		} else {
			// updateMember 에 주소문자열 세팅
			String addr = String.join("^^^", memberAddress);
			updateMember.setMemberAddress(addr);
		}
		
		// 로그인한 회원의 번호를 updateMember에 세팅
		updateMember.setMemberNo( loginMember.getMemberNo() );
		
		
		// DB 회원 정보 수정 (update) 서비스 호출
		int result = service.updateInfo(updateMember);
		
		String message = null;
		
		// 결과값으로 성공
		if(result > 0) {
			// -> 성공 시 Session에 로그인된 회원 정보도 수정(동기화)
			System.out.println("내 정보 수정 성공"); // 확인용 콘솔창
			
			loginMember.setMemberNickname( updateMember.getMemberNickname() );
			loginMember.setMemberTel( updateMember.getMemberTel() );
			loginMember.setMemberAddress( updateMember.getMemberAddress() );
			
			message = "회원 정보 수정 성공";
			
		} else {
			// 실패에 따른 처리
			System.out.println("내 정보 수정 실패"); // 확인용 콘솔창
			
			message = "회원 정보 수정 실패";
		}
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:info"; // 상대경로 (절대경로로 작성하려면? /myPage/myPage-info)
	}
	
	
	/* MultipartFile : input type="file"로 제출된 파일을 저장한 객체
	 * 
	 * [ 제공하는 메서드 ]
	 * - transferTo() : 파일을 지정된 경로에 저장(메모리 -> HDD/SSD)
	 * - getOriginalFileName() : 파일 원본명
	 * - getSize() : 파일 크기
	 * 
	 * 
	 * */
	
	
	
	// 프로필 이미지 수정
	@PostMapping("/profile")
	public String updateProfile(
			@RequestParam("profileImage") MultipartFile profileImage // 업로드 파일
			, HttpSession session // 세션 객체
			, @SessionAttribute("loginMember") Member loginMember
			, RedirectAttributes ra // 리다이렉트 시 메세지 전달
			) throws Exception{
		
		// 웹 접근 경로 (webapp 기준)
		String webPath = "/resources/images/member/";
		
		// 실제로 이미지 파일이 저장되어야하는 서버컴퓨터 경로
		String filePath = session.getServletContext().getRealPath(webPath);
		// C:\workspace\7_Framework\boardProject\src\main\webapp\resources\images\member
		
		
		// 프로필 이미지 수정 서비스 호출
		int result = service.updateProfile(profileImage, webPath, filePath, loginMember);
		
		
		String message = null;
		if(result > 0) message = "프로필 이미지가 변경되었습니다";
		else			message = "프로필 변경 실패";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:profile";
	}

}
