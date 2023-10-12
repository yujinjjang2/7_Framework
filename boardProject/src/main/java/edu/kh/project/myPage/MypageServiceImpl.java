package edu.kh.project.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dto.Member;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	private myPageDAO dao;
	
	@Transactional
	@Override
	public int updateInfo(Member updateMember) {
		return dao.updateInfo(updateMember);
	}
	
	
}
