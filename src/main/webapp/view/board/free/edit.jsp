<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp"%>

<div class = "container" style="margin-top:5%; min-height: 500px">
	<h3>자유게시판</h3>
	<form action="${pageContext.request.contextPath}/freeboard/edit?free_no=${param.free_no}" method="post" id="editform">
		<fieldset>
		<legend>글 수정</legend>
		<div class="form-group">
			<label for="userID">이름</label>
			<input type="text" id="userID" name="userID" class="form-control" placeholder="${userID}" readonly >
		</div>		
		<div class="form-group">
			<label for="free_password">비밀번호</label>
			<input type="text" id="free_password" name="free_password" class="form-control">
			<span>(*)수정, 삭제시 필수</span>
		</div>		
		<div class="form-group">
			<label for="free_title">제목</label>
			<input type="text" id="free_title" name="free_title" class="form-control" value="${dto.free_title}">
		</div>
		<div class="form-group">
			<label for="free_content">내용</label>
			<textarea name="free_content" id="free_content" cols="60" rows="10" class="form-control">${dto.free_content}</textarea>
		</div>		
		<div class = "form-group text-right">
			<input type="submit" value="입력" class="btn btn-danger">
			<input type="reset" value="취소" class="btn btn-default">
			<a href="${pageContext.request.contextPath}/freeboard" class="btn btn-info">목록보기</a>			
		</div>		
		</fieldset>
	</form>
</div>
<%@include file="/inc/footer.jsp"%>	