<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>공연 예술 소식 사이트</title>
	
	<!-- favicon -->
	<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
	
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
	
	<!-- My CSS -->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/navbar.css?ver=1">
	
	<!-- jQuery library -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!--
		// alert 메세지 띄우기 (메인페이지 URL에서 alert하기 위해 쓴 방법 cf.다른 더 좋은 방법 있나?)
		if(request.getHeader("Cookie")!=null) {
			Cookie[] cookies = request.getCookies();
			for(Cookie c: cookies) {
				if(c.getName().equals("alertMessage")) {
					String value = c.getValue();
	                String decodedValue = URLDecoder.decode(value, "UTF-8");
	                out.println("<script>window.onload = alert('" + decodedValue + "'); </script>");
	                // 잘 안먹힘. setTime 줘서 고쳐보기
	                c.setMaxAge(0);
	                response.addCookie(c);
	                break;
				}
			}
		}  -->
<script>
	$(document).ready(function() {
	    let name = "alertMessage";
	    let decodedCookie = decodeURIComponent(document.cookie.replace(/\+/g, ' '));
	    let ca = decodedCookie.split(';');
	    for(let i = 0; i < ca.length; i++) {
	        let c = ca[i];
	        while (c.charAt(0) === ' ') {
	            c = c.substring(1);
	        }
	        if (c.indexOf(name) === 0) {
	            // 현재 쿠키 삭제
	            let expires = "expires=Thu, 01 Jan 1970 00:00:00 UTC";
	            let path = "path=/data";
	            document.cookie = name + "=; " + expires + "; " + path;
	            // 0.1초 후에 alert 메시지 표시
				setTimeout(function() {
                	alert(c.substring(name.length + 1, c.length));
            	}, 100);
				break;
	        }
	    }
	});
</script>
	<!-- JSTL 이용시 참고
		로그인 한 경우 세션 객체에 다음과 같이 setAttribute 해놓았음.
		(사용자) "userID" : userID
		(관리자) "admin" : userAuthority
	-->
	
	<!-- 로그인 한 경우 아이디와 관리자인지 세션 받아오기 -->
	<c:set var="userID" value="${userID}"/>
	<c:set var="admin" value="${admin}"/>
	
	<nav id="navbar" class="navbar navbar-default">
		<div class="navbar_logo">
			<h1><a href="${pageContext.request.contextPath}">공연 예술 소식 사이트</a></h1>
		</div>
		<ul  id="navbar_menu" class="nav navbar-nav navbar-right">
		
			<c:if test="${!empty admin}">
				<li><a href="${pageContext.request.contextPath}/art_info">공연소식 관리</a></li>
				<!-- <li><a href="">공연 캘린더 관리</a></li> -->
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티 관리<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/notice">공지사항 관리</a></li>
						<li><a href="${pageContext.request.contextPath}/freeboard">자유게시판 관리</a></li>
						<li><a href="${pageContext.request.contextPath}/reviewboard">공연후기 게시판 관리</a></li>
						<li><a href="${pageContext.request.contextPath}/inquiryboard">1:1 문의하기 관리</a></li>
					</ul>
				</li>
			</c:if>
			
			<c:if test="${empty admin}">
				<li><a href="${pageContext.request.contextPath}/art_info">공연소식</a></li>
				<!-- <li><a href="${pageContext.request.contextPath}/art_info">공연 캘린더</a></li> -->
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/notice">공지사항</a></li>
						<li><a href="${pageContext.request.contextPath}/freeboard">자유게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/reviewboard">공연후기 게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/inquiryboard">1:1 문의하기</a></li>
					</ul>
				</li>
			</c:if>
			
		</ul>
		
		<ul id="navbar_login" class="nav navbar-nav navbar-right">
		
			<c:if test="${!empty admin}">
				<li><a href="${pageContext.request.contextPath}/admin">회원관리</a></li>
				<li><a href="${pageContext.request.contextPath}/login">로그아웃</a></li>
			</c:if>
			
			<c:if test="${empty admin && !empty userID}">
				<li><a href="${pageContext.request.contextPath}/mypage">마이 페이지</a></li>
				<li><a href="${pageContext.request.contextPath}/login">로그아웃</a></li>
			</c:if>
			
			<c:if test="${empty userID}">
			<li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
			</c:if>
			
		</ul>
	</nav><br>
	
	<script>
		$("#navbar_login").css('font-weight', 'bold');
	</script>