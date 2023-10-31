package com.kh.test.user.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.test.user.model.dao.UserDAO;
import com.kh.test.user.model.vo.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO dao;

	@Override
	public User selectUserInfo(String id) {
		
		return dao.selectUserInfo(id);
	}

}
