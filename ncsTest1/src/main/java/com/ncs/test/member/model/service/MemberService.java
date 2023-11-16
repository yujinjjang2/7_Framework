package com.ncs.test.member.model.service;

import com.ncs.test.member.model.vo.Member;

public interface MemberService {

	/** loginMember 조회
	 * @param member
	 * @return Member
	 */
	Member memberLogin(Member member);

}
