<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp"%>
<script src="https://cdn.ckeditor.com/4.21.0/standard/ckeditor.js"></script>
<body>
	<div class="container panel panel-default">
		<form
			action="${pageContext.request.contextPath}/reviewboard/edit?re_no=${re_no}"
			method="post">
			<h3 class="panel-heading">
				<img class="img1" alt="img"
					src="${pageContext.request.contextPath}/images/user.png" />
			</h3>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td><input type="text" name="re_title" id="re_title"
							class="form-control" placeholder="${param.re_title}"></td>
					</tr>

					<tr>
						<td><textarea class="form-control" id="re_content"
								name="re_content" placeholder="${dto.re_content}">${param.re_content}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="div1">
				<input type="submit" value="작성" name="submit" id="submit"
					class="btn btn-danger text-right" title="제출하기"> 
				<input type="reset" value="취소" class="btn btn-info"> 
				<a href="${pageContext.request.contextPath}/reviewboard" class="btn btn-info">목록보기</a>
			</div>
		</form>
	</div>

	<script> 
		$(function() {
			CKEDITOR.replace('re_content');
		});
		$(".img1").css("width", "3%");
		$(".p1").css("margin", "-37px 0px 7px 42px");
		/* $(function() {
		    $('#re_title').on('input', function() {
		        if($(this).val().trim() === "") {
		            alert('제목을 입력해주세요.');
		        }
		    });
		    $('#re_content').on('input', function() {
		        if($(this).val().trim() === "") {
		            alert('제목을 입력해주세요.');
		        }
		    });
		}); */   //그냥 알람창 안띄우고 , 리턴하는 방향으로 수정.
		</script>
</body>
</html>