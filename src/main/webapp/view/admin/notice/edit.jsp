<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>

<c:set var="n" value="${dto}"/>

<div class="container">
    <div class="row">
     <form id="form" method="post" action="${pageContext.request.contextPath}/notice/edit?no_no=${n.no_no}">
         <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
             <thead>
                 <tr>
                     <th colspan="2" style="background-color:#eeeeee; text-align:center;">공지사항 수정하기</th>
                 </tr>
             </thead>
             <tbody>
                 <tr>
                     <td>
                     	<input id="${n.no_title}" type="text" class="form-control" placeholder="공지사항 제목" name="no_title" maxlength="50" value="${n.no_title}">
                     </td>
                 </tr>
                 <tr>
                     <td>
                     	<textarea id="${n.no_content}" class="form-control" placeholder="공지사항 내용" name="no_content" maxlength="2048" style="height:350px">${n.no_content}</textarea>
                     </td>
                 </tr>
             </tbody>
         </table>
         <div class="text-center">
         	<input id="submit" type="submit" class="btn btn-primary" value="공지사항 수정">
         </div>
     </form>
    </div>
 </div>
<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			if( $("td > input").val() == $("td > input").attr('id') &&
				$("td > textarea").val() == $("td > textarea").attr('id') )	{
					alert("제목 또는 내용을 수정해주세요.");
					return false;
			}
			if( $("td > input").val() == "" || $("td > textarea").val() == "" )	{
				alert("제목과 내용을 입력해주세요.");
				return false;
			}
		});
	});
</script>
</body>
</html>
