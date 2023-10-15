<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>detailPageSe</title>

    <link rel="stylesheet" href="../../../resources/css/detailPageSe.css">

    <script src="https://kit.fontawesome.com/f821b57119.js" crossorigin="anonymous"></script>
    
    <script src="../../../resources/js/main.js"></script>
</head>
<body>
    <section>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />
        <!-- top -->
        <div id="top">"Seoul"</div>

        <!-- main -->
        <div id="main">
            <!-- 왼쪽 여백 -->
            <div id="leftArea"></div>

            <!-- 메인 이미지들 -->
            <div class="mainArea">
                <div class="imgArea">
                    <!-- for문 돌리기 이미지 -->
					<c:forEach var="img" items="${list}" varStatus="vs">
	                    <div class="placeSec">
	                        <div class="blank"></div>
	                        <div class="placeImg">
	                        	<input type="hidden" id="imagePath_${img.imageNo}" value=${img.imagePath}>
	                        	<input type="hidden" id="imageName_${img.imageNo}" value=${img.imageName}>
	                            <img src=${img.imagePath}  class="contImg" onclick="travleImgDetail(${img.imageNo})">
	                        </div>
	                        <div class="placeTitSec">
	                            <div class="tit">
	                                ${img.imageName}
	                            </div>
	                            <div class="delArea">
	                                <button type="button" class="xBtn" onclick="travleImgDel(${img.imageNo})">X</button>
	                            </div>
	                        </div>
	                    </div>
					</c:forEach>

                    <!-- 네번째 네개 이미지(+) -->
                    <div class="placeSec" id="addPlus">
                        <div class="blank"></div>
                        <div class="placeImg">
                            <button type="button" id="plusBtn">+</button>
                        </div>
                        <div class="placeTitSec">
                            <div class="tit"></div>
                            <div class="delArea"></div>
                        </div>
                    </div>
                </div>    
            </div>

            <!-- 오른쪽 여백 -->
            <div id="rightArea"></div>
        </div>

        <!-- bottom -->
        <div id="bottom"><jsp:include page="/WEB-INF/views/common/footer.jsp" /></div>
    </section>
    
    <a href="#top"><img src="../../../resources/images/BTN/탑버튼.png" id="upBtn"></a>
</body>
</html>