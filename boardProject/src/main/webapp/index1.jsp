<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>

	<h2>회원 정보 조회(아이디 검색)</h2>
	
	<form action="/select/UserInfo" method="POST">
		<input type="text" name="id" placeholder="회원 아이디 입력">
		<button type="submit">조회</button>
	</form>
	
</body>
</html>