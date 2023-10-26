package edu.kh.project.main.model.websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

										// TextWebSocketHandler: text 형식(ex.채팅)으로 주고받을 때 사용하는 Handler
public class TestWebSocketHandler extends TextWebSocketHandler{

	// Set : 중복 X, 순서 X
	// synchronizedSet : 동기화된 Set 객체 반환
	private Set<WebSocketSession> sessions
		= Collections.synchronizedSet(new HashSet<>());
	
	
	// WebSocketSession : 클라이언트 - 서버 간 전이중통신을 담당하는 객체
	//					 클라이언트의 세션을 가로채서 저장하고 있음    // 세션? 접속한 클라이언트 1개당 생성
	

	// - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
	@Override                              // (가로 챈) 클라이언트 세션
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	
		// 클라이언트 웹소켓 연결을 요청하면 sessions에
		// 클라이언트와 전이중통신을 담당하는 객체 WebSocketSession을 추가
		sessions.add(session);
	}

	// - 클라이언트로부터 텍스트 메세지를 받았을 때 실행
	@Override                                                  // 클라이언트가 보낸 메세지(ex. Hi)
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		System.out.println("전달 받은 내용 : " + message.getPayload());
		
		// /testSock으로 연결된 객체를 만든 클라이언트들(sessions)에게
		// 전달받은 내용을 보냄
		for(WebSocketSession s:sessions) {
			s.sendMessage(message);
		}
		
	}

	// - 클라이언트와 연결이 종료되면 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// sessions에서 나간 클라이언트의 정보를 제거
		sessions.remove(session);
	}
	

	
	
	
}






/* WebSocket
- 브라우저와 웹서버간의 전이중통신을 지원하는 프로토콜이다
- HTML5버전부터 지원하는 기능이다.
- 자바 톰캣7버전부터 지원했으나 8버전부터 본격적으로 지원한다.
- spring4부터 웹소켓을 지원한다. 
(전이중 통신(Full Duplex): 두 대의 단말기가 데이터를 송수신하기 위해 동시에 각각 독립된 회선을 사용하는 통신 방식. 
대표적으로 전화망, 고속 데이터 통신)



WebSocketHandler 인터페이스 : 웹소켓을 위한 메소드를 지원하는 인터페이스
    -> WebSocketHandler 인터페이스를 상속받은 클래스를 이용해 웹소켓 기능을 구현


WebSocketHandler 주요 메소드
       
    ** 순서대로 실행 **    
        
    void afterConnectionEstablished(WebSocketSession session)
    - 클라이언트와 연결이 완료되고, 통신할 준비가 되면 실행
    
    void handlerMessage(WebSocketSession session, WebSocketMessage message)
    - 클라이언트로부터 메세지가 도착하면 실행
    
    void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus)
    - 클라이언트와 연결이 종료되면 실행

    void handleTransportError(WebSocketSession session, Throwable exception) -> 부가적인 사항
    - 메세지 전송중 에러가 발생하면 실행 


----------------------------------------------------------------------------

TextWebSocketHandler :  WebSocketHandler 인터페이스를 상속받아 구현한 텍스트 메세지 전용 웹소켓 핸들러 클래스
 
    handlerTextMessage(WebSocketSession session, TextMessage message)
    - 클라이언트로부터 '텍스트 메세지'를 받았을때 실행          ->   텍스트 == 글자
     




*/

