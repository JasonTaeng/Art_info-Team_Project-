<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css?ver=1">

<%
	// 아이디 저장하기 쿠키 확인
	String remember_check = "";
	String userID = null;
	boolean isOn = false;
	String cookie = request.getHeader("Cookie");
	if(cookie != null) {
		Cookie[] cookies = request.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("remember")) {
				isOn = true;
				userID = c.getValue();
				remember_check = "checked";
				break;
			}
		}
	}
%>
<div class="login_wrapper">
     <form id="login_form" method="post" action="${pageContext.request.contextPath}/login">
     	<h2>로그인 화면</h2>
        <input type="text" name="userID" placeholder="아이디" value="<%=isOn? userID:""%>" autofocus>
        <input type="password" name="userPassword" placeholder="비밀번호">
        <label for="remember_check">
            <input type="checkbox" id="remember_check" name="remember_check" <%= remember_check%> >
            <span>아이디 저장하기</span>
        </label>
        <!-- <a href="#" class="idpw_search">아이디/비밀번호 찾기</a> -->
        <input type="submit" value="로그인" style="cursor:pointer">
    </form>
    <div class="join" onClick="location.href='${pageContext.request.contextPath}/join'" style="cursor:pointer">
    	<a href="${pageContext.request.contextPath}/join">회원가입</a>
    </div>
</div>

<script>
	$(document).ready(function() {
		$("#login_form").submit(function() {
			if($("input[name=userID]").val()=="") {
				alert('아이디를 입력해주세요.');
				return false;
			} else if($("input[name=userPassword]").val()=="") {
				alert('비밀번호를 입력해주세요.');
				return false;
			}
		});
	});
</script>
</body>
</html>