<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>	

<c:set var="b" value="${boardDto}"/>

<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
            <thead>
                <tr>
                    <th colspan="3" style="background-color:#eeeeee; text-align:center;">공지사항 보기</th>
                </tr>
            </thead>
           <tbody>
               <tr>
                   <td style="width:20%;">공지사항 제목</td>
                   <td colspan="2">${b.no_title}</td>
               </tr>
               <tr>
                   <td>첨부파일</td>
                   <td>
                   <c:if test="${empty fileName}">
	                   없음
                   </c:if>
                   <c:if test="${!empty fileName}">
                   		<c:set var="style" value="font-weight: bold; color:red;"/>
                   		<c:forTokens var="f" delims="," items="${fileName}" varStatus="status">
                   			<c:set var="delim" value="${status.last? '' : '&nbsp;/&nbsp;'}"/>
	                   		<a download href="${pageContext.request.contextPath}/download/notice/${f}" onClick="checkLoginDown(event, ${userID})" style="${style}">${f}</a>
	                   		${delim}
                   		</c:forTokens>
                   </c:if>
                   </td>
               </tr>
               <tr>
                    <td>작성자</td>
                    <td colspan="2">${b.fk_user_userID}</td>
               </tr>
               <tr>
                   <td>등록일</td>
                   <td colspan="2">
	                   <fmt:parseDate var="no_publish_date" value="${b.no_publish_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		               <fmt:formatDate value="${no_publish_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
	               </td>
               </tr>
               <tr>
                   <td>내용</td>
                   <td colspan="2" style="min-height:200px;">${b.no_content}</td>
               </tr>
               <tr>
                   <td>조회수</td>
                   <td colspan="2">${b.no_hit}</td>
               </tr>
               <tr>
                   <td>IP</td>
                   <td colspan="2">${b.no_ip}</td>
               </tr>
           </tbody>
       </table>
	</div>
</div>
<br><br>

<!-- 댓글 달기 -->
<div class="container">
    <div class="row">
    	<form action="${pageContext.request.contextPath}/notice/comment/write?no_no=${b.no_no}" method="post">
    	    <div class="form-group">
			    <label for="comment">댓글 작성:</label>
		        <textarea id="comment" class="form-control" onClick="checkLoginComm(${userID})" placeholder="댓글 작성" name="no_comm_content" maxlength="2048" style="height:100px"></textarea>
		    </div>
		    <div class="text-center">
		    	<input type="submit"class="btn btn-info" value="댓글 달기">
		    </div>
		</form>
	</div>
</div>

<br><br>

<c:if test="${!empty commentList}">
<!-- 댓글 보여주기 -->
<div class="container">
    <div class="row">
        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
            <thead>
                <tr>
                    <th colspan="3" style="background-color:#eeeeee; text-align:center;">댓글</th>
                </tr>
            </thead>
            <tbody>
			<c:forEach var="c" items="${commentList}" begin="0" end="0">
                <tr>
                    <td style="width:20%;">작성자</td>
                    <td colspan="2">${c.fk_user_userID}</td>
                </tr>
                <tr>
                    <td>작성일</td>
                    <td colspan="2">
	                    <fmt:parseDate var="no_comm_date" value="${c.no_comm_date}" pattern="yyyy-MM-dd HH:mm:ss"/>
		                <fmt:formatDate value="${no_comm_date}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
	                </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td colspan="2" style="min-height:200px;"><b>${c.no_comm_content}</b></td>
                </tr>
			 </c:forEach>
	         </tbody>
	     </table>
	</div>
	
	<c:forEach var="c" items="${commentList}" begin="1">
	<div class="row">
        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
            <tbody>
                <tr>
                    <td style="width:20%;">작성자</td>
                    <td colspan="2">${c.fk_user_userID}</td>
                </tr>
                <tr>
                    <td>작성일</td>
                    <td colspan="2">
	                    <fmt:parseDate var="no_comm_date" value="${c.no_comm_date}" pattern="yyyy-MM-dd" />
		                <fmt:formatDate value="${no_comm_date}" pattern="yyyy년 MM월 dd일"/>
	                </td>
                </tr>
                <tr>
                    <td>내용</td>
                    <td colspan="2" style="min-height:200px;"><b>${c.no_comm_content}</b></td>
                </tr>
	         </tbody>
	     </table>
	</div>
	</c:forEach>
</div>
</c:if>
<script>
	function checkLoginDown(event, userID) {
		if(userID=='' || userID==null) {
			alert("로그인 해주세요.");
			event.preventDefault();
		}
	}
	function checkLoginComm(userID) {
		if(userID=='' || userID==null) {
			alert("로그인 해주세요.");
			$("#comment").blur(); // 커서 포커스 아웃
		}
	}
</script>
</body>
</html>
