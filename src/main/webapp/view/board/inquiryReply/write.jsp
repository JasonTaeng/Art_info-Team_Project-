<%@ include file="/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inquiry.css?ver=1">
	
<form action="${pageContext.request.contextPath}/inquiryReply/write"
	method="post" id="writeform">
	<div class="container r-write">
		<h3>답변하기</h3>
		<div class="form-group">
			<label for="in_reply_content">내용</label>
			<textarea rows="10" cols="60" name="in_reply_content" id="in_reply_content"
				class="form-control"></textarea>
		</div>
		<div class="form-group text-right">
			<div class="modal-footer">
			<input type="hidden" name="in_reply_no" value="${param.in_reply_no}">
			<input type="hidden" name="in_reply_check" value="y">
			<input type="hidden" name="userID" value="${userID}">
				<input type="submit" value="입력" class="btn btn-info">
				<button class="btn btn-danger" onclick="history.go(-1)">취소하기</button>
			</div>
		</div>
	</div>
</form>

<%@ include file="/inc/footer.jsp"%>