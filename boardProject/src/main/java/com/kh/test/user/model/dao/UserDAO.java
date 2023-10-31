package com.kh.test.user.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.test.user.model.vo.User;

@Repository
public class UserDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	public User selectUserInfo(String id) {
		
		return sqlSession.selectOne("userMapper.selectUserInfo", id);
	}

}
