<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>

		<div class="container panel panel-default">
		<h3 class="panel-default">공연소식 올리기</h3>
		<form action="${pageContext.request.contextPath}/art_info/write" method="post" enctype="multipart/form-data" id="form1">
			<fieldset>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td>
							<label for="art_title">제목</label>
						 	<input type="text" id="art_title" name="art_title" class="form-control" placeholder="제목">
						</td>
					</tr>
					<tr>
						<td><label for="art_img_data">파일명:</label> <br>
						<input type="file" id="art_img_data"  name="file"></td>
					</tr>
					<tr>
						<td>
							<label for="art_content">내용</label>
						 	<input type="text" id="art_content" name="art_content" class="form-control" 
						 	placeholder="${dto.art_content}">
						</td>
					</tr>
					<tr>
						<td>
							<label for="art_location">장소</label>
							<input type="text" id="art_location" name="art_location" class="form-control">
						</td>
					</tr>
					<tr>
						<td>
							<p>기간</p>
							 시작시간  <input type="datetime-local" id="start_date" name="start_date" ><br>
							 종료시간  <input type="datetime-local" id="end_date" name="end_date" ><br>
						</td>
					</tr>
					<tr>
						<td>
							<label for="art_duration">관람시간</label>
							<input type="text" id="art_duration" name="art_duration" class="form-control" required>
						</td>
					</tr>
					<tr>
						<td>
							<label for="art_price">가격</label>
							<input type="text" id="art_price" name="art_price" class="form-control" >
						</td>
					</tr>
					<tr>
						<td>
							<label for="art_age">관람연령</label>
							<input type="text" id="art_age" name="art_age" class="form-control" >
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

</body>
</html>