package edu.kh.project.myPage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository
public class myPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int updateInfo(Member updateMember) {
		
		return sqlSession.update("myPageMapper.updateInfo", updateMember);
	}

	/** 프로필 이미지 수정
	 * @param loginMember
	 * @return result
	 */
	public int updateProfileImage(Member loginMember) {
		return sqlSession.update("myPageMapper.updateProfileImage", loginMember);
	}

 	/** 비밀번호 변경
	 * @param loginMember
	 * @return result
	 */
	public int changePwUpdate(Member loginMember) {
		
		return sqlSession.update("myPageMapper.changePwUpdate", loginMember);
	}

	/** 회원 탈퇴
	 * @param loginMember
	 * @return result
	 */
	public int memberDelete(Member loginMember) {
		
		return sqlSession.update("myPageMapper.memberDelete", loginMember);
	}
}
