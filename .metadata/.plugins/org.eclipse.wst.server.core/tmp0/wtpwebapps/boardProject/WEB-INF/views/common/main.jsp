<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>프로젝트</title>
	
	<!-- fontaswesom 아이콘 사용할 수 있는 스크립트 연결-->
	<script src="https://kit.fontawesome.com/f821b57119.js" crossorigin="anonymous"></script>
</head>
<body>
	<main>
        
        
        <%-- header.jsp 추가 --%>
        
        <%--
        	<jsp:include page="jsp파일경로" />
        	
        	- jsp 파일 경로는 'webapp 폴더 기준'으로 작성
        	- JSP 액션 태그(jsp에 기본 내장됨)
        	- 다른 jsp 파일의 코드를 현재 위치에 추가
        
         --%>
         
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
		

        <section class="content">
            <section class="content-1">
            	<h3>로그인된 회원 정보</h3>
             	${sessionScope.loginMember}
             	
             	
             	<h3>닉네임이 일치하는 회원의 전화번호 조회</h3>
            	
            	<input type="text" id="inputNickname">
            	<button id="btn1">조회</button>
            	<h4 id="result1"></h4>
            	
            	<hr>
            	
            	<h3>이메일을 입력받아 일치하는 회원의 정보를 조회</h3>
            	<input id="inputEmail">
            	<button id="btn2">조회</button>
            	<ul id="result2">
            	</ul>
            
            
            </section>
            

            <!-- 아이디/비밀번호/로그인버튼 영역 -->
            <section class="content-2">
            
            	<c:choose>
            		<%-- 로그인이 안되었을때 --%>
            		<%-- EL empty : 비어있거나 null이면 true --%>
            		<c:when test="${empty sessionScope.loginMember}">
            		
	            		<form action="/member/login" method="post" name="login-form" id="loginFrm">
	
		                    <fieldset class="id-pw-area">
		                        <section>
		                            <input type="text" name="memberEmail" placeholder="이메일"
		                            	autocomplete="off"
		                            	value="${cookie.saveId.value}"
		                            	>
		                            <input type="password" name="memberPw" placeholder="비밀번호">
		                        </section>
		
		                        <section>
		                            <button>로그인</button>
		                        </section>
		                    </fieldset>
		
		                    <label>
		                    
		                    	<c:if test="${not empty cookie.saveId.value}">
		                    		<%-- 쿠키에 저장된 이메일이 있으면 변수 선언 : save --%>
		                    		
		                    		<c:set var="save" value="checked"/>
		                    	</c:if>
		                    	
		                        <input type="checkbox" name="saveId" ${save}> 아이디 저장
		                        
		                    </label>
		
		                    <!-- 회원가입/ Id/pw 찾기 영역 -->
		                    <section class="signup-find-area">
		                        <a href="/member/signUp">회원가입</a>
		                        <span>|</span>
		                        <a href="#">ID/PW 찾기</a>
		                    </section>
	
	                	</form>
            		</c:when>
            		
            		<%-- 로그인이 되었을때 --%>
            		<c:otherwise>
            			<article class="login-area">
            				
            				<a href="#">
            					<img src="/resources/images/user.png" id="memberProfile">
            				</a>
            				
            				<div class="my-info">
            					<div>
            						<a href="#" id="nickname">${sessionScope.loginMember.memberNickname}</a>
            						<a href="/member/logout" id="logoutBtn">로그아웃</a>
            					</div>
            					
            					<p></p>
            				</div>
            				
            			</article>
            			
            			
            		</c:otherwise>
            	</c:choose>
            
            

            </section>

        </section>
       

    </main>
    
    <jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<!-- main.js 추가 -->
	<script src="/resources/js/main.js"></script>

</body>
</html>