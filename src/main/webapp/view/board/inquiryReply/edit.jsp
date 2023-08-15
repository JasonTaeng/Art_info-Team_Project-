<%@ include file="/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inquiry.css?ver=1">
<form
	action="${pageContext.request.contextPath}/inquiryReply/edit"
	method="post" id="editform">
	<div class="container r-edit">
		<h3>답변수정하기</h3>
		<div class="form-group">
			<label for="in_reply_content">내용</label>
			<textarea rows="10" cols="60" name="in_reply_content" id="in_reply_content"
				 class="form-control"></textarea>
		</div>
		<div class="form-group text-right">
			<div class="modal-footer">
				<input type="hidden" id="in_reply_no" name="in_reply_no" value="${param.in_reply_no}">
				<input type="submit" class="btn btn-info" value="수정확인">
				<button type="button" class="btn btn-danger" onclick="history.go(-1)">취소하기</button>
				
			</div>
		</div>
</div>
</form>
<script>
    document.addEventListener("DOMContentLoaded", function() {
    	var in_reply_content = "${param.in_reply_content}";
        document.getElementById("in_reply_content").value = in_reply_content;
    });
</script>
<script>
	$(document).ready(function(){
					$("#editform").submit(function(){
						if( $("#in_reply_content").val() == "") {
							alert("제목을 입력하세요.");
							$("#in_reply_content").focus();
							return false;
						}
					});
				});
	</script>


<%@ include file="/inc/footer.jsp"%>