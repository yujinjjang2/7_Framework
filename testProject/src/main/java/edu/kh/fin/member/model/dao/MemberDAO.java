package edu.kh.fin.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.fin.member.model.dto.Member;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public Member login(Member member) {
		
		return sqlSession.selectOne("memberMapper.login", member);
	}

	public String selectMemberTel(String nickname) {
		
		return sqlSession.selectOne("memberMapper.selectMemberTel", nickname);
	}

}
