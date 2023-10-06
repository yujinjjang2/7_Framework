package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

public interface AjaxService {

	/** 닉네임으로 전화번호 조회
	 * @param nickname
	 * @return tel
	 */
	String selectMemberTel(String nickname);

	/** 이메일로 회원정보 조회
	 * @param email
	 * @return
	 */
	Member selectMember(String email);

	/** 이메일 중복검사
	 * @param email
	 * @return count
	 */
	int checkEmail(String email);

	/** 닉네임 중복검사
	 * @param nickname
	 * @return count
	 */
	int checkNickname(String nickname);
}
