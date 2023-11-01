package edu.kh.project.member.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 비즈니스 로직(데이터 가공, DAO 호출, 트랜잭션 제어)처리하는 클래스 명시
		// + Bean으로 등록하는 어노테이션
public class MemberServiceImpl implements MemberService{
	
	// org.slf4j.Logger : 로그를 작성할 수 있는 객체
	// org.slf4j.LoggerFactory                        // 현재 클래스명 넣어주어야 함! (현재 클래스에 logger 사용하겠다)
	private Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	
	// @Autowired : 작성된 필드와
	// Bean으로 등록된 객체 중 타입이 일치하는 Bean을
	// 해당 필드에 자동 주입(Injection) 하는 어노테이션.
	// == DI(Dependency Injection, 의존성 주입)
	// -> 객체를 직접 만들지 않고, Spring이 만든걸 주입함(Spring에 의존)
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI(의존성 주입)
	private BCryptPasswordEncoder bcrypt;
	
	// 암호화가 필요한 곳? 로그인, 회원가입
	
	@Override
	public Member login(Member inputMember) {
		
		// 로그 출력
		logger.info("MemberServiceImpl.login() 실행"); // 정보 출력
		logger.debug("memberEmail : " + inputMember.getMemberEmail() );
		logger.warn("이건 경고 용도");
		logger.error("이건 오류 발생 시");
		
		
		// 암호화가 다 다른지 test 용도
//		System.out.println("암호화 확인 1:"  +  bcrypt.encode(  inputMember.getMemberPw()  )  );
//		System.out.println("암호화 확인 2:"  +  bcrypt.encode(  inputMember.getMemberPw()  )  );
//		System.out.println("암호화 확인 3:"  +  bcrypt.encode(  inputMember.getMemberPw()  )  );
//		System.out.println("암호화 확인 4:"  +  bcrypt.encode(  inputMember.getMemberPw()  )  );
//		System.out.println("암호화 확인 5:"  +  bcrypt.encode(  inputMember.getMemberPw()  )  );
		
		
		// dao 메서드 호출
		Member loginMember = dao.login(inputMember);
		
		// 입력받은 비밀번호(평문) --비교-- DB에서 조회한 비밀번호(암호화)
		
		if(loginMember != null) { // 아이디가 일치하는 회원이 조회된 경우
			
			// 입력한 pw, 암호화된 pw 같은지 확인
			
			// 같을 경우            // 로그인 시 입력             // DB에서 조회한 것
			if(bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				
				// 비밀번호를 유지하지 않기 위해서 로그인 정보에서 제거
				loginMember.setMemberPw(null);
				
			} else { // 다를 경우(비밀번호 잘못쳤을 경우)
				loginMember = null;
			}
			
		} 
		
		return loginMember;
	}

	
	// 회원 가입 서비스
	@Transactional
	@Override
	public int signUp(Member inputMember) {
		
		// 비밀번호 암호화 (Bcrypt) 후 다시 inputMember 세팅
		String encPw = bcrypt.encode(inputMember.getMemberPw());
		inputMember.setMemberPw(encPw);
		
		return dao.signUp(inputMember);
	}
	
	
}
