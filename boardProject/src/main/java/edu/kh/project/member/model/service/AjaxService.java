package edu.kh.project.member.model.service;

public interface AjaxService {

	/** 닉네임으로 전화번호 조회
	 * @param nickname
	 * @return tel
	 */
	String selectMemberTel(String nickname);

}
