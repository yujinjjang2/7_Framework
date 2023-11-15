package edu.kh.fin.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.fin.member.model.dao.MemberDAO;
import edu.kh.fin.member.model.dto.Member;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public Member login(Member member) {
		
		Member loginMember = dao.login(member);
		
		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
			
			// 입력한 pw, 암호화된 pw 같은지 확인
			
			// 같을 경우            // 로그인 시 입력             // DB에서 조회한 것
			if(bcrypt.matches(member.getMemberPw(), loginMember.getMemberPw())) {
				
				// 비밀번호를 유지하지 않기 위해서 로그인 정보에서 제거
				loginMember.setMemberPw(null);
				
			} else { // 다를 경우(비밀번호 잘못쳤을 경우)
				loginMember = null;
			}
			
		}
		
		return loginMember;
	}

	@Override
	public String selectMemberTel(String nickname) {
		
		return dao.selectMemberTel(nickname);
	}
	
}
