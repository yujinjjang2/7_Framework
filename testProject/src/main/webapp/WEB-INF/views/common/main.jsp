<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>로그인 하기</h1>
	
	<form action="/member/login" method="post">
		아이디 : <input type="text" id="id" name="memberEmail"> <br>
		비밀번호 : <input type="password" id="pw" name="memberPw">
		<button type="submit">로그인</button>
	</form>
	
	
	
	<h3>닉네임이 일치하는 회원의 전화번호 조회</h3>
            	
   	<input type="text" id="inputNickname">
   	<button id="btn1">조회</button>
   	<h4 id="result1"></h4>
	
	<script src="/resources/js/main.js"></script>
</body>
</html>