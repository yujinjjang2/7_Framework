package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.project.board.model.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dao.BoardDAO2;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;
import edu.kh.project.common.utility.Util;

@Service
public class BoardServiceImpl2 implements BoardService2{

	@Autowired
	private BoardDAO2 dao;

	// 게시글 삽입
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int boardInsert(Board board, List<MultipartFile> images, String webPath, String filePath) 
			throws IllegalStateException, IOException {
		
		// 0. XSS 방지 처리
		board.setBoardContent( Util.XSSHandling( board.getBoardContent() ) /*XSS 방지 처리를 한 내용*/ );
		board.setBoardTitle( Util.XSSHandling( board.getBoardTitle() )/*XSS 방지 처리를 한 내용*/ );
		
		
		// 1. BOARD 테이블 INSERT 하기 (제목, 내용, 작성자, 게시판 코드)
		// -> boardNo (시퀀스로 생성한 번호) 반환 받기
		int boardNo = dao.boardInsert(board);
		
		
		// 2. 게시글 삽입 성공 시
		// 업로드된 이미지가 있다면 BOARD_IMG 테이블에 삽입하는 DAO 호출
		if(boardNo > 0) { // 게시글 삽입 성공 시
			
			// List<MultipartFile> images
			// -> 업로드된 파일이 담긴 객체 MultipartFile이 5개 존재
			// -> 단, 업로드된 파일이 없어도 MultipartFile 객체는 존재
			
			// 실제 업로드된 파일의 정보를 기록할 List
			List<BoardImage> uploadList = new ArrayList<BoardImage>();
			
			// images에 담겨있는 파일 중 실제 업로드된 파일만 분류
			for(int i = 0; i < images.size(); i++) {
				
				// i번째 요소에 업로드된 파일이 있다면
				if(images.get(i).getSize() > 0) {
					
					BoardImage img = new BoardImage();
					
					// img 에 파일 정보를 담아서 uploadList 에 추가
					img.setImagePath(webPath); // 웹 접근 경로
					img.setBoardNo(boardNo); // 게시글 번호
					img.setImageOrder(i); // 이미지 순서

					// 파일 원본명
					String fileName = images.get(i).getOriginalFilename();
					
					img.setImageOriginal(fileName); // 원본명
					img.setImageReName( Util.fileRename(fileName) ); // 변경명
					
					
					uploadList.add(img);
					
				}
				
			} // 분류 for 문 종료
			
			
			// 분류 작업 후 uploadList가 비어 있지 않은 경우
			// == 업로드한 파일이 있다
			if( !uploadList.isEmpty() ) {
				
				// BOARD_IMG 테이블에 insert 하는 dao 호출
				int result = dao.insertImageList(uploadList);
				// result == 삽입된 행의 개수
				
				// 전체 insert 성공 여부 확인
				// 삽입된 행의 개수와 uploadList 의 개수가 같다면
				// == 전체 insert 성공
				if(result == uploadList.size()) {
					
					// 서버에 파일을 저장 (transferTo)
					
					for(int i = 0; i < uploadList.size(); i++) {
						
						int index = uploadList.get(i).getImageOrder();
						
						String rename = uploadList.get(i).getImageReName();
						
						images.get(index).transferTo( new File(filePath + rename) );
						
					}
					
					
					
				} else { // 일부 또는 전체 insert 실패
					
					// rollback 필요
					
					// @Transactional(rollbackFor = Exception.class)
					// -> 예외가 발생 해야지만 롤백
					
					// [결론]
					// 예외를 강제 발생 시켜서 rollback 해야된다
					// 사용자 정의 예외 만들어 발생시키자!
					
					
					throw new FileUploadException();
				}
				
			}
			
		}
		
		
		return boardNo;
	}
}
