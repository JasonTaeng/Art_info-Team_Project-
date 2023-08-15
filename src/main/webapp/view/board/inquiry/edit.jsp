<%@ include file="/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inquiry.css?ver=1">
<form action="${pageContext.request.contextPath}/inquiryboard/edit" method="post" id="editform">
<div class="container edit">
		<h3>1:1질문수정하기</h3>
		<div class="form-group">
			<label for="in_title">제목</label> 
			<input type="text" id="in_title" name="in_title" class="form-control">
		</div>
		<div class="form-group">
			<label for="userID">작성자</label><input type="text" id="userID"
				name="userID" value="${dto.fk_user_userID}" class="form-control"
				readonly="readonly">

		</div>
		<div class="form-group">
			<label for="in_password">비밀번호</label> <input type="password"
				id="in_password" name="in_password" class="form-control">
		</div>
		<div class="form-group">
			<label for="in_password_check">비밀번호확인</label> <input type="password"
				id="in_password_check" name="in_password_check"
				placeholder="${dto.in_password}" class="form-control">
		</div>
		<div class="form-group">
			<label for="in_content">내용</label>
			<textarea rows="10" cols="60" name="in_content" id="in_content"
				placeholder="${dto.in_content}" class="form-control"></textarea>
		</div>
		<div class="form-group text-right">
			<div class="modal-footer">
				<input type="hidden" id="in_no" name="in_no" value="${param.in_no}">
				<input type="submit" class="btn btn-info" value="수정하기">
				<button type="button" class="btn btn-danger" onclick="history.go(-1)">취소하기</button>
				
			</div>
		</div>
	</div>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        document.getElementById("in_title").value = "${dto.in_title}";
        document.getElementById("in_password").value = "${dto.in_password}";
        document.getElementById("in_password_check").value = "${dto.in_password}";
        document.getElementById("in_content").value = "${dto.in_content}";
    });
</script>
<script>
	$(document).ready(function(){
					$("#editform").submit(function(){
						if( $("#in_title").val() == "") {
							alert("제목을 입력하세요.");
							$("#in_title").focus();
							return false;
						}
						if( $("#in_password").val() == "") {
							alert("비밀번호를 입력하세요.");
							$("#in_password").focus();
							return false;
						}
						if( $("#in_password_check").val() == "") {
							alert("비밀번호를 입력하세요.");
							$("#in_password_check").focus();
							return false;
						}
						if ($("#in_password").val() != $("#in_password_check").val()) {
							alert("비밀번호가 일치하지 않습니다. 확인해주세요.");
							$("#in_password").val("");
							$("#in_password_check").val("");
							$("#in_password").focus();
							return false;
						}
						if( $("#in_content").val() == "") {
							alert("내용을 입력하세요");
							$("#in_content").focus();
							return false;
						}
					});
				});
	</script>


<%@ include file="/inc/footer.jsp"%>