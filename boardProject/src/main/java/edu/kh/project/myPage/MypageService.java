package edu.kh.project.myPage;

import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.member.model.dto.Member;

public interface MypageService {

	int updateInfo(Member updateMember);

	int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws Exception;

	/** 비밀번호 변경
	 * @param loginMember
	 * @param newPw
	 * @return result
	 */
	int changePwUpdate(Member loginMember, String newPw);

	/** 회원 탈퇴
	 * @param loginMember
	 * @return result
	 */
	int memberDelete(Member loginMember);

}
