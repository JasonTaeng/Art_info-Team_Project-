<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp"%>

<div class = "container" style="margin-top:5%; min-height: 500px">
	<h3>작성 글 상세보기</h3>		
	<div class = "panel">	
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">이름</span> <p>${dto.fk_user_userID}</p>
		</div>
	</div>
	<div class = "panel">	
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">제목</span> <p>${dto.free_title}</p>
		</div>
	</div>
	<div class = "panel">
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">내용</span>
			<textarea class="form-control">${dto.free_content}</textarea>
		</div>
	</div>	
	<div class = "panel">		
		<div class="panel-body">
			<span class="glyphicon glyphicon-plus">조회수</span> <p>${dto.free_hit}</p>
		</div>	
	</div>	
		<div class="text-right">
			<c:if test="${userID == fk_user_userID}">
				<a href="${pageContext.request.contextPath}/freeboard/edit?free_no=${dto.free_no}" class="btn btn-danger">수정</a>
				<a href="${pageContext.request.contextPath}/freeboard/delete?free_no=${dto.free_no}" class="btn btn-danger">삭제</a>
			</c:if>
			<a href="${pageContext.request.contextPath}/freeboard" class="btn btn-info">목록보기</a>
		</div>					
	</div>	
<%@include file="/inc/footer.jsp"%>