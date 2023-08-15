<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
	
	<div class = "container" style="margin-top:5%; min-height: 500px">
	<h4>제목 : ${dto.re_title}</h4>		
	<div>
          <img class="img1" alt="이미지" src="${contextPage.request.contextPath}/data/images/user.png"> 
          <p class="p1">${dto.fk_user_userID} ${dto.re_ip}
          <br/>
          ${dto.re_date} 조회수: ${dto.re_hit}
          <c:choose>
    	  <c:when test="${userID == fk_user_userID}">
          <a href="${pageContext.request.contextPath}/reviewboard/edit?re_no=${dto.re_no}">수정</a>
          <a href="${pageContext.request.contextPath}/reviewboard/delete?re_no=${dto.re_no}" >지우기</a>
          </c:when>
          </c:choose>
          </p> 		
    </div>
    <div class = "panel">
		<div class="form-group">
        	<table>
        		<tr>
        			<td>
        				<c:if test="${!empty fileName}">
        					<img src="${pageContext.request.contextPath}/upload/reviewboard/${fileName}" alt="이미지">
        				</c:if>
        			</td>
        		</tr>
        		<tr>
        			<td>${dto.re_content}</td>
        		</tr>
        	</table>
        </div>
        <div class="text-right">
		<c:choose>
    	   <c:when test="${userID == fk_user_userID}">
			<a href="${pageContext.request.contextPath}/reviewboard/edit?re_no=${dto.re_no}" class="btn btn-danger">수정</a>
			<a href="${pageContext.request.contextPath}/reviewboard/delete?re_no=${dto.re_no}" class="btn btn-danger">삭제</a>
		</c:when>
		</c:choose>
			<a href="${pageContext.request.contextPath}/reviewboard" class="btn btn-info">목록보기</a>
		
		</div>		
	</div>
	</div>	
					
	<script>
		$(".img1").css("width", "3%");
		$(".p1").css("margin", "-37px 0px 7px 42px");
	</script>
</body>
</html>