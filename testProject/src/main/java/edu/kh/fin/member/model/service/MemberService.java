package edu.kh.fin.member.model.service;

import edu.kh.fin.member.model.dto.Member;

public interface MemberService {

	Member login(Member member);

	String selectMemberTel(String nickname);

}
