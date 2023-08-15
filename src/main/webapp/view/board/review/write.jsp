<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
<script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
<body>
	<div class="container panel panel-default">
		<h5 class="panel-heading">
			<img alt="이미지" src="${pageContext.request.contextPath}/images/user.png">${userID}
		</h5>
		<form action="${pageContext.request.contextPath}/reviewboard/write?userID=${userID}" method="post" id="form1" enctype="multipart/form-data">
			<fieldset>
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>
								<label for="file">파일명:</label><input type="file" name="file">
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" id="re_title" name="re_title" class="form-control" placeholder="제목">
							</td>
						</tr>
						<tr>
							<td>
								<textarea class="form-control" id="re_content" name="re_content" placeholder="내용을 입력해주세요"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" value="입력" name="submit" class="btn btn-warning" title="제출하기">
								<input type="reset" value="취소" name="reset" class="btn btn-danger">
							</td>
						</tr>
					</tbody>
				</table>
			</fieldset>
		</form>
	</div>
	<br><hr><br>

	<script>
		/*$(function() {
			CKEDITOR.replace('re_content');
		});*/
		$(function() {
			$("#form1").submit(function() {
				if ($("#re_title").val() == "") {
					alert("제목을 입력해야합니다.");
					$("#re_title").focus();
					return false;
				}
				if ($("#re_content").val() == "") {
					alert("내용을 입력해야합니다.");
					$("#re_content").focus();
					return false;
				}
			});
		});
	</script>
</body>
</html>