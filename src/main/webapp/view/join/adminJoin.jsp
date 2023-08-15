<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/join.css?ver=1">

<div class="login_wrapper">
	<form id="login_form" method="post" action="${pageContext.request.contextPath}/admin/adminjoin">
		<h2>일반 관리자 등록</h2>
		<input type="text" name="userID" placeholder="아이디" maxlength="20" autofocus>
		<input type="password" name="userPassword" placeholder="비밀번호" maxlength="20">
		<input type="text" name="userName" placeholder="이름" maxlength="20">
		<div class ="form-group" style="text-align: center;">
			<div class="btn-group" data-toggle="buttons">
			    <label class="btn btn-primary active">
			        <input type="radio" name="userGender" autocomplete="off" value="남" checked>남자
			    </label>
			    <label class="btn btn-primary">
			        <input type="radio" name="userGender" autocomplete="off" value="여">여자
			    </label>
			</div><br>
		</div>
		<input type="email" name="userEmail" placeholder="이메일" maxlength="20">
		<input type="text" name="userPhoneNum" placeholder="핸드폰 번호" maxlength="20">
		<input type="submit" value="일반 관리자 등록" style="cursor:pointer">
	</form>
</div>
</body>
</html>