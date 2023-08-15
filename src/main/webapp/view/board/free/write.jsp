<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp"%>

<div class="container" style="margin-top:5%; min-height:500px">
	<h3>자유게시판</h3>
		<form action="${pageContext.request.contextPath}/freeboard/write" method="post" id="form">
			<fieldset>
			<legend>글 작성</legend>
			<div class="form-group">
				<label for="fk_user_userID">작성자</label>
				<input type="text" id="fk_user_userID" name="fk_user_userID" class="form-control" placeholder="${userID}" value="${userID}" readonly>
			</div>		
			<div class="form-group">
				<label for="free_password">비밀번호</label>
				<input type="password" id="free_password" name="free_password" class="form-control">
			</div>
			<div class="form-group">
				<label for="free_title">제목</label>
				<input type="text" id="free_title" name="free_title" class="form-control">
			</div>					
			<div class="form-group">
				<label for="free_content">내용</label>
				<textarea name="free_content" id="free_content" cols="60" rows="10" class="form-control"></textarea>
			</div>		
			<div class = "form-group text-right">
				<input type="submit" value="입력" class="btn btn-danger" style="color: white; background-color: #f4511e">
				<input type="reset" value="취소" class="btn btn-default">
				<a href="${pageContext.request.contextPath}/freeboard" class="btn btn-info">목록보기</a>			
			</div>						
			</fieldset>
		</form>
</div>

<script>
	$(document).ready(function() {
		$("#form").submit(function() {
		if($("#userID").val() == "") {
			alert("작성자가 작성되지 않았습니다.\n 확인해주세요."); $("#userID").focus(); return false;
		}
		if($("#free_password").val() == "") {
			alert("패스워드가 입력되지 않았습니다.\n 확인해주세요."); $("#free_password").focus(); return false;
		}
		if($("#free_title").val() == "") {
			alert("제목이 입력되지 않았습니다.\n 확인해주세요."); $("#free_title").focus(); return false;
		}
		if($("#free_content").val() == "") {
			alert("내용이 입력되지 않았습니다.\n 확인해주세요."); $("#free_content").focus(); return false;
		}
		});
	});
	</script>

<%@include file="/inc/footer.jsp"%>