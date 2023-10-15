package edu.kh.project.myPage;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.common.utility.Util;
import edu.kh.project.member.model.dto.Member;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	private myPageDAO dao;
	
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI(의존성 주입)
	private BCryptPasswordEncoder bcrypt;

	// 내 정보 수정 서비스
	@Transactional
	@Override
	public int updateInfo(Member updateMember) {
		return dao.updateInfo(updateMember);
	}

	// 프로필 이미지 수정 서비스
	@Override
	public int updateProfile(MultipartFile profileImage, String webPath, String filePath, Member loginMember) throws Exception {
		
		// 프로필 이미지 변경 실패 대비
		String temp = loginMember.getProfileImage(); // 기존에 가지고 있던 이전 이미지 저장
		
		
		String rename = null; // 변경 이름 저장 변수
		
		if(profileImage.getSize() > 0) { // 업로드된 이미지가 있을 경우
			
			// 1) 파일 이름 변경
			rename = Util.fileRename(profileImage.getOriginalFilename());
			
			// 2) 바뀐 이름 loginMember에 세팅
			loginMember.setProfileImage(webPath + rename); // /resources/images/member/20230510163910_12345.jpg

			
			
		} else { // 업로드된 이미지가 없는 경우 (x버튼: 기존 이미지가 있었다가 삭제한 경우) 
			
			loginMember.setProfileImage(null);
			
		}
		
		
		// 프로필 이미지 수정 DAO 메서드 호출
		int result = dao.updateProfileImage(loginMember);
		
		if(result > 0) { // DB에 이미지 경로 업데이트 성공했다면
			
			// 업로드된 새 이미지가 있을 경우
			if(rename != null) {
				
				// 메모리에 임시 저장되어있는 파일을 서버에 진짜로 저장하는 것
				profileImage.transferTo(new File(filePath + rename));
			}
			
		} else { // 실패
			
			// 이전 이미지로 프로필 다시 세팅
			loginMember.setProfileImage(temp);
			
		}
		
		return result;
		
	}

	// 비밀번호 변경
	@Override
	public int changePwUpdate(Member loginMember, String newPw) {

		// 비밀번호 암호화 (Bcrypt) 후 다시 loginMember 세팅
		String encPw = bcrypt.encode(newPw);		
		loginMember.setMemberPw(encPw);
		
		return dao.changePwUpdate(loginMember);
	}

	// 회원 탈퇴
	@Override
	public int memberDelete(Member loginMember) {
		
		return dao.memberDelete(loginMember);
	}
	
	
}
