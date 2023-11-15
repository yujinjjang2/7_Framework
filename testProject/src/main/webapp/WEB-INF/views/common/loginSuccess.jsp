<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공 페이지</title>
</head>
<body>

	<h1>회원정보</h1>
	
	<table border="1">
		<tr>
			<td>회원번호</td>
			<td>회원이메일</td>
			<td>회원닉네임</td>
			<td>회원전화번호</td>
			<td>회원주소</td>
			<td>회원프로필이미지</td>
			<td>회원등록일자</td>
		</tr>
		
		<tr>
			<td>${loginMember.memberNo}</td>
			<td>${loginMember.memberEmail}</td>
			<td>${loginMember.memberNickname}</td>
			<td>${loginMember.memberTel}</td>
			<td>${loginMember.memberAddress}</td>
			<td>${loginMember.profileImage}</td>
			<td>${loginMember.enrollDate}</td>
		</tr>
	</table>
	
</body>
</html>