<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp"%>

<div class="container" style="margin-top:5%; min-height:500px">
	<h3>자유게시판 글 삭제</h3>
	<form action="${pageContext.request.contextPath}/freeboard/delete?free_no=${param.free_no}" method="post" id="deleteForm">
		<div class="form-group">
			<label for="free_password">비밀번호</label>
			<input type="password" name="free_password" id="free_password" class="form-control"> 
			<span>(*) 삭제시 필수입니다.</span>
		</div>
		<div class="form-group">
			<input type="submit" value="확인" class="btn btn-danger"/>
			<input type="reset" onclick="history.go(-1);" value="취소" class="btn btn-info">
		</div>
	</form>
</div>
<%@include file="/inc/footer.jsp"%>