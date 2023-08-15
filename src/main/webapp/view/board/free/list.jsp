<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp"%>

<div class = "container text-center">
	<h3>자유게시판</h3>
</div>

<div class = "container text-right">
		<h3 class="hidden">검색폼</h3>
		<form class="table-form">
			<fieldset>
				<legend class="hidden">검색 필드</legend>
				<label class="hidden">분류</label>
				<select name="search">
					<option ${(param.search == "free_title") ? "selected" : ""} value="free_title">제목</option>
					<option ${(param.search == "fk_user_userID") ? "selected" : ""} value="fk_user_userID">작성자</option>
				</select>
				<label class="hidden">검색어</label>
				<input type="text" name="searchvalue" value="${param.searchvalue}">
				<input type="submit" class="btn btn-info" value="검색"> 
			</fieldset>
		</form>
</div>

<div class = "container text-center">	
	<table class = "table table-striped">
	<caption>자유롭게 글을 작성하세요.</caption>		
		<colgroup>
				<col style="width:20%"/>
				<col style="width:20%"/>
				<col style="width:20%"/>
				<col style="width:20%"/>
				<col style="width:20%"/>
		</colgroup>	
		
	<thead>
		<tr><th scope="col">번호</th><th scope="col">제목</th>
		    <th scope="col">작성자</th><th scope="col">날짜</th>
		    <th scope="col">조회수</th></tr>
	</thead>
	<tbody>
		<c:forEach var="dto" items="${paging.list10}" varStatus="status">
			<tr><td>${paging.pageTotal - paging.pstartno - status.index}</td>
			<td><a href="${pageContext.request.contextPath}/freeboard/detail?free_no=${dto.free_no}">
			${dto.free_title}</a></td>
			<td>${dto.fk_user_userID}</td>
			<td>${dto.free_date}</td>
			<td>${dto.free_hit}</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
			<tr>
				<td colspan="5" class="text-center">
					<ul class="pagination">
						<c:if test="${paging.bottomStart >= paging.bottomPageLimit }">
							<li><a
								href="${pageContext.request.contextPath}/freeboard?free_no=${(paging.bottomStart-2)*paging.onePageLimit}">이전</a></li>
						</c:if>
						<!-- 	1 2 3 4 5 6 7 8 9 10	 -->
						<c:forEach var="i" begin="${paging.bottomStart}"
							end="${paging.endStart}">
							<li
								<c:if test="${paging.bottomCurrent == i}"> class="active"</c:if>><a
								href="${pageContext.request.contextPath}/freeboard?free_no=${(i-1)*paging.onePageLimit}"
								title=""> ${i} </a></li>
						</c:forEach>
						<!-- 	다음		 -->
						<c:if test="${paging.endStart < paging.bottomPageAll }">
							<li><a
								href="${pageContext.request.contextPath}/freeboard?free_no=${(paging.endStart)*paging.bottomPageLimit}">다음</a></li>
						</c:if>
					</ul>
				</td>
			</tr>
		</tfoot>	
	</table>
	<c:if test="${!empty userID}">
		<p class="text-right"><a href="${pageContext.request.contextPath}/freeboard/write" class="btn btn-danger">글쓰기</a>
		</p>
	</c:if>		
</div>
<script>
	$("table th").css('text-align', 'center');
</script>
<%@include file="/inc/footer.jsp"%>