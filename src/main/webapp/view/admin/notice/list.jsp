<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>

<!-- 검색 -->
<div class="container text-right">
	<div class="row">
		<a id="notice-reg" href="${pageContext.request.contextPath}/notice/write" class="btn btn-primary">공지사항 등록</a>
		<form class="table-form">
			<br>
			<fieldset>
				<select name="type">
					<option ${(param.type == "title") ? "selected" : ""} value="title">제목</option>
					<option ${(param.type == "ID") ? "selected" : ""} value="ID">작성자</option>
				</select>
				<input type="text" name="keyword" value="${param.keyword}">
				<input type="submit" class="btn btn-info" value="검색"> 
			</fieldset>
		</form>
		<br>
	</div>
</div>

<div class="container">

	<div class="row">
		<table class="table table-striped table-hover" style="text-align: center; border: 1px solid #dddddd">
			
			<thead>
				<tr>
					<th scope="col">번호</th>
					<th scope="col">작성자 / 작성일자</th>
					<th scope="col">제목</th>
					<th scope="col">공개여부 / 공개일자</th>
					<th scope="col">조회수</th>
					<th scope="col"><input id="check-publish" type="checkbox"></th>
					<th scope="col"><input id="check-no-pub" type="checkbox"></th>
					<th scope="col"><input id="check-delete" type="checkbox"></th>
				</tr>
			</thead>
			
			<tbody>
				<!--페이지 넘버에 따라 받아온 리스트 출력-->
				<c:set var="page" value="${param.page}"/>
				<c:set var="startPageNum" value="${page-(page-1)%5}"/>
				<c:set var="lastPageNum" value="${Math.ceil(lastNum/10)}"/>
				<c:if test="${!empty list}">
				<c:forEach var="l" items="${list}" varStatus="status">
					<tr onClick="location.href = '${pageContext.request.contextPath}/notice/detail?no_no=${l.no_no}'" title="공지사항으로 이동합니다." style="cursor:pointer;">
	                    <td>${lastNum - status.index - (page-1)*10}</td>
	                    <c:set var="style" value="font-weight: bold; color:red;"/>
	                    <td>${l.fk_user_userID} /
	                    	<fmt:parseDate var="no_date" value="${l.no_date}" pattern="yyyy-MM-dd"/>
	                    	<fmt:formatDate value="${no_date}" pattern="yyyy년 MM월 dd일"/>
	                    </td>
	                    <td>
	                    	<strong>${l.no_title}</strong>
	                    	<span style="${style}">[${l.cntComment}]</span>
	                    </td>
	                    <td>${l.no_publish} /
	                    	<c:if test="${l.no_publish=='공개'}">
		                    	<fmt:parseDate var="no_publish_date" value="${l.no_publish_date}" pattern="yyyy-MM-dd"/>
		                    	<fmt:formatDate value="${no_publish_date}" pattern="yyyy년 MM월 dd일"/>
	                    	</c:if>
	                    	<c:if test="${l.no_publish=='비공개'}">
	                    		N/A
	                    	</c:if>
	                    </td>
	                    <td>${l.no_hit}</td>
	                   	<td>
	                   		<input class="check-publish" type="checkbox" value="${l.no_no}">
	                   	</td>
	                    <td>
	                   		<input class="check-no-pub" type="checkbox" value="${l.no_no}">
	                   	</td>
	                   	<td>
	                   		<input class="check-delete" type="checkbox" value="${l.no_no}">
	                   	</td>
	            	</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty list}">
					<tr><td colspan="8">
						검색 결과가 없습니다.
					</td></tr>
				</c:if>
            </tbody>
        </table>
	</div>
	
	<div class="row text-right">
		<form id="form-publish" action="${pageContext.request.contextPath}/notice/update_delete" method="post">
			<input type="hidden" name="publish" value="ok">
			<input type="hidden" name="no_no" value="">
			<input type="submit" class="btn btn-success" value="공개">
		</form>
		<form id="form-no-pub" action="${pageContext.request.contextPath}/notice/update_delete" method="post">
			<input type="hidden" name="noPublish" value="ok">
			<input type="hidden" name="no_no" value="">
			<input type="submit" class="btn btn-info" style="background-color: black;" value="비공개">
		</form>
		<form id="form-delete" action="${pageContext.request.contextPath}/notice/update_delete" method="post">
			<input type="hidden" name="delete" value="ok">
			<input type="hidden" name="no_no" value="">
			<input type="submit" class="btn btn-danger" value="삭제">
		</form>
	</div>
	
	<!-- 페이저 처리 -->
	<div class="text-center">
		<ul class="list-inline pagination">
			<c:if test="${startPageNum>1}">
				<c:if test="${empty param.type}">
					<li><a class="btn btn-prev" href="${pageContext.request.contextPath}/notice?page=${startPageNum-5}">이전</a></li>
				</c:if>
				<c:if test="${!empty param.type}">
					<li><a class="btn btn-prev" href="${pageContext.request.contextPath}/notice?page=${startPageNum-5}&type=${param.type}&keyword=${param.keyword}">이전</a></li>
				</c:if>
			</c:if>
			<c:if test="${startPageNum==1}">
				<li><span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span></li>
			</c:if>
			
			<c:forEach var="i" begin="0" end="${(startPageNum+4<=lastPageNum)? 4 : lastPageNum-startPageNum}">
				<li ${page==(startPageNum+i)? ' class="active" ' : ''}>
					<c:if test="${empty param.type}">
						<a class="-text- orange bold" href="${pageContext.request.contextPath}/notice?page=${startPageNum+i}">
							${startPageNum+i}
						</a>
					</c:if>
					<c:if test="${!empty param.type}">
						<a class="-text- orange bold" href="${pageContext.request.contextPath}/notice?page=${startPageNum+i}&type=${param.type}&keyword=${param.keyword}">
							${startPageNum+i}
						</a>
					</c:if>
				</li>
			</c:forEach>
			
			<c:if test="${startPageNum+4<lastPageNum}">
				<c:if test="${empty param.type}">
					<li><a class="btn btn-next" href="${pageContext.request.contextPath}/notice?page=${startPageNum+5}">다음</a></li>
				</c:if>
				<c:if test="${!empty param.type}">
					<li><a class="btn btn-next" href="${pageContext.request.contextPath}/notice?page=${startPageNum+5}&type=${param.type}&keyword=${param.keyword}">다음</a></li>
				</c:if>
			</c:if>
			<c:if test="${startPageNum+4>=lastPageNum}">
				<li><span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span></li>
			</c:if>
		</ul>
	</div>
	
</div>
<br>

<script>
	// th 태그 가운데 정렬
	$("table th").css('text-align', 'center');
	
	// 체크박스 크기 조절
	$("input[type='checkbox']").css({
		'width': '15px',
		'height': '15px'
	});
	// 공지사항 등록 버튼 정렬
	/*$("#notice-reg").css({
		'display': 'inline-block',
		'margin-right', '20px'
	});*/
	
	// form 제출 버튼 크기 조절, 수평 정렬
	$("#form-no-pub, #form-publish, #form-delete").css({
		'display': 'inline-block',
		'padding': '0'
	});
	
	$(document).ready(function() {
		// 체크박스 전체 선택
		$("#check-no-pub").click(function() {
			$(".check-no-pub").click();
		});
		$("#check-publish").click(function() {
			$(".check-publish").click();
		});
		$("#check-delete").click(function() {
			$(".check-delete").click();
		});
		
		// 체크 박스 이벤트 핸들러(이벤트 전파 막아서 detail 페이지 이동 차단, form에 값 글 번호 넣기)
		$(".check-publish").click(function(event) {
		    event.stopPropagation();
		    var no_noArray = [];
		    $(".check-publish:checked").each(function() {
		    	no_noArray.push($(this).val());
		    });
		    $("#form-publish input[name='no_no']").val(no_noArray.join(','));
		});
		$(".check-no-pub").click(function(event) {
		    event.stopPropagation();
		    var no_noArray = [];
		    $(".check-no-pub:checked").each(function() {
		    	no_noArray.push($(this).val());
		    });
		    $("#form-no-pub input[name='no_no']").val(no_noArray.join(','));
		});
		$(".check-delete").click(function(event) {
		    event.stopPropagation();
		    var no_noArray = [];
		    $(".check-delete:checked").each(function() {
		    	no_noArray.push($(this).val());
		    });
		    $("#form-delete input[name='no_no']").val(no_noArray.join(','));
		});
		
		// 체크박스 미 선택시 form 제출 차단
		$("#form-publish").submit(function(){
			if($(".check-publish:checked").length==0) {
				alert('공개할 글을 선택하세요');
				return false;
			}
		});
		$("#form-no-pub").submit(function(){
			if($(".check-no-pub:checked").length==0) {
				alert('비공개할 글을 선택하세요');
				return false;
			}
		});
		$("#form-delete").submit(function(){
			if($(".check-delete:checked").length==0) {
				alert('삭제할 글을 선택하세요');
				return false;
			}
		});
	});
</script>
</body>
</html>