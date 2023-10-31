<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>searchSuccess</title>
</head>
<body>

	<h2>회원정보</h2>
	
	<table>
		<tr>
			<td>회원번호</td>
			<td>회원아이디</td>
			<td>회원이름</td>
			<td>회원나이</td>
		</tr>
		
		<tr>
			<td>${user.userNo}</td>
			<td>${user.userId}</td>
			<td>${user.userName}</td>
			<td>${user.userAge}</td>
		</tr>
	</table>
	
</body>
</html>