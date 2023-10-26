<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<link rel="stylesheet" href="/resources/css/main-style.css">

	<header>

            <!-- 클릭 시 메인페이지로 이동하는 로고 -->
            <section>
                <a href="/">
                    <img src="/resources/images/logo.jpg" id="homeLogo">
                </a>
            </section>

            <!-- 검색창 부분 -->
            <section>
                <section class="search-area">
                    <!-- form 내부 input 태그 값을 서버 또는 페이지로 전달 -->
                    <form action="/search" method="GET" name="search-form">

                        <!-- fieldset : form 내부에서 input을 종류별로 묶는 용도로 자주 사용 -->
                        <fieldset>

                            <!-- search : 텍스트 타입과 기능적으로는 똑같으나,
                                브라우저에 의해 다르게 표현될 수 있음. -->
                            <!-- autocomplete : HTML 기본 자동완성 사용 X -->
                            <input type="search" id="query" name="query"
                                autocomplete="off" placeholder="회원을 닉네임으로 검색해주세요."
                            >

                            <button id="searchBtn" class="fa-solid fa-magnifying-glass"></button>
                        </fieldset>

                    </form>

                </section>
            </section>

            <section></section>
        </header>

        <!-- 보통은 header안에 작성하나 사이드에 nav가 있는 경우도 있기 때문에 따로 작성해본다! -->
        <nav>
            <ul>
            	<%--
                <li><a href="#">공지사항</a></li>
                <li><a href="#">자유게시판</a></li>
                <li><a href="#">질문게시판</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="#">1:1문의</a></li>
                 --%>
                 
                 <%-- interceptor를 이용해서 조회된 boardTypeList를
                 	application scope에서 얻어와 화면에 출력
                 --%>
                  
                 <%--
                 [
                 {BOARD_NAME=공지사항, BOARD_CODE=1},
                 {BOARD_NAME=자유 게시판, BOARD_CODE=2},
                 {BOARD_NAME=테스트 게시판, BOARD_CODE=3},
                 {BOARD_NAME=질문 게시판, BOARD_CODE=4},
                 {BOARD_NAME=점심 게시판, BOARD_CODE=5}
                 ]
                  --%>
                 <c:forEach var="boardType" items="${boardTypeList}">
                 	<li>
                 		<a href="/board/${boardType.BOARD_CODE}">${boardType.BOARD_NAME}</a>
                 	</li>
                 </c:forEach> 
                 
                <%-- 로그인 했을 때 채팅 보여짐 --%>
		        <c:if test="${not empty loginMember}" >
		            <li><a href="/chatting">채팅</a></li>
		        </c:if>
                 
            </ul>
        </nav>
        
        