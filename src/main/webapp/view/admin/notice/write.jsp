<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>

<div class="container">
    <div class="row">
     <form id="form" method="post" action="${pageContext.request.contextPath}/notice/write" enctype="multipart/form-data">
         <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
             <thead>
                 <tr>
                     <th colspan="2" style="background-color:#eeeeee; text-align:center;">공지사항 등록하기</th>
                 </tr>
             </thead>
             <tbody>
                 <tr>
                     <td>공지사항 제목</td>
                     <td>
                     	<input type="text" class="form-control" placeholder="제목" name="no_title" maxlength="50">
                     </td>
                 </tr>
                 <tr>
                  	 <td>첨부파일</td>
                     <td>
                     	<input type="file" name="file">
                     </td>
                 </tr>
                 <tr>
                  	 <td>첨부파일</td>
                     <td>
                     	<input type="file" name="file">
                     </td>
                 </tr>
                 <tr>
                 	 <td>공지사항 내용</td>
                     <td>
                     	<textarea class="form-control" placeholder="내용" name="no_content" maxlength="2048" style="height:350px"></textarea>
                     </td>
                 </tr>
                 <tr>
                 	 <td class="text-right"><label for="checkbox">바로 공개</label></td>
                 	 <td class="text-left">
                 	 	<input id="checkbox" name="publish" type="checkbox" value="ok">
                 	 </td>
                 </tr>
             </tbody>
         </table>
         <div class="text-center">
         	<input id="submit" type="submit" class="btn btn-primary" value="공지사항 등록">
         </div>
     </form>
    </div>
 </div>
<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			if( $("td > input[name='no_title']").val() == "" || $("td > textarea[name='no_content']").val() == "" )	{
				alert("제목과 내용을 입력해주세요.");
				return false;
			}
		});
	});
</script>
</body>
</html>
