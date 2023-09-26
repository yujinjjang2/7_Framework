<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<!-- footer는 주요 콘텐츠가 아니라서 보통 main 태그 안에 작성하지 않음. -->
<footer>
   <p>Copyright &copy; KH Information Educational Institute E-Class</p>

   <section>
       <a href="#">프로젝트 소개</a>
       <span>|</span>
       <a href="#">이용약관</a>
       <span>|</span>
       <a href="#">개인정보처리방침</a>
       <span>|</span>
       <a href="#">고객센터</a>
   </section>
</footer>

<%-- session에 message가 존재할 경우 --%>
<c:if test="${not empty sessionScope.message}">
	
	<script>
		// EL/JSTL 구문이 먼저 해석
		// 문자열의 경우 따옴표가 없는 상태이니 옆에 붙여줘야함.
		<%-- alert 안에는 문자열만 들어와야 함 --%>
		alert('${message}') // ${message}
	</script>
	
	<%--
		session에 message를 추가하면
		브라우저 종료 또는 만료 전까지 계속 메세지가 출력된다
	
		-> 1회 출력 후 session에서 message 삭제
	 --%>
	 
	 <c:remove var="message" scope="session"/>
	
	
</c:if>