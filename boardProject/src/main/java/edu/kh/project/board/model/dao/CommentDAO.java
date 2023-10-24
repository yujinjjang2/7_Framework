package edu.kh.project.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 댓글 목록 조회
	public List<Comment> select(int boardNo) {
		
		return sqlSession.selectList("boardMapper.selectCommentList", boardNo);
	}
	

}
