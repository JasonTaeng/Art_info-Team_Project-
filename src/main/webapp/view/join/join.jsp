<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/join.css?ver=1">

<div class="login_wrapper">
	<form id="login_form" method="post" action="${pageContext.request.contextPath}/join">
	    <h2>회원가입 화면</h2>
	    <div id="ID-align">
		    <input type="text" id="userID" name="userID" placeholder="아이디" maxlength="20" autofocus>
		    <input type="button" value="중복확인" id="checkID" name="checkID">
	    </div>
	    <div class="result"></div>
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
        <input type="submit" value="회원가입" style="cursor:pointer">
    </form>
    <div class="join" onClick="location.href= '${pageContext.request.contextPath}/login' " style="cursor:pointer">
    	<a href="${pageContext.request.contextPath}/login" id="login">로그인</a>
    </div>
</div>
<script>
	// 수평 정렬
	$("#ID-align").css("justify-content", "center");
	
	$(document).ready(function() {
		// 아이디에 특수문자 포함 안되게 막아야 함. -- 미구현.
		
		// 모든 사항 미 입력시 제출 차단
		$("#login_form").submit(function() {
			if($("input[name=userID]").val()=="" || $("input[name=userPassword]").val()=="" ||
			   $("input[name=userName]").val()=="" || $("input[name=userEmail]").val()=="" ||
			   $("input[name=userPhoneNum]").val=="") {
				alert('모든 사항을 입력해주세요.');
				return false;
			}
		});
		
		// 중복 체크 버튼 클릭 이벤트 처리
		$("#checkID").click(function() {
			$.ajax({
				url: "${pageContext.request.contextPath}/join/checkid", // 중복 체크를 처리할 서버 요청 URL
				type: "GET",
				dataType: "text",
				data: { "userID": $("#userID").val() },
				success: function(data) {
					$(".result").html(data);
				},
				error: function(xhr, textStatus, errorThrown) {
					$(".result").html(textStatus + (" http-" + xhr.status + "/" + errorThrown));
				}
			});
		});
	});
</script>
</body>
</html>