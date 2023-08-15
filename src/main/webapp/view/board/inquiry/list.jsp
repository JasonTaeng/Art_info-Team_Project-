<%@include file="/inc/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inquiry.css?ver=1">

<div class="container panel panel-warning">
	<h3 class="panel-heading">1:1문의</h3>
	<table class="table table-striped my-table">
		<thead class="table tt">
			<tr>
				<th  scope="col">번호</th>
				<th scope="col">답변여부</th>
				<th scope="col">제목</th>
				<th scope="col">작성자</th>
				<th scope="col">작성일자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${paging.list10}" varStatus="status">
				<tr >
					<td >${paging.pageTotal - paging.pstartno - status.index}</td>
		
					<c:choose>
						<c:when test="${!empty dto.in_set}">
							<!-- 답변이 있는 경우 -->
							<c:set var="answerStatus" value="답변완료" />
						</c:when>
						<c:otherwise>
							<!-- 답변이 없는 경우 -->
							<c:set var="answerStatus" value="미답변" />
						</c:otherwise>
					</c:choose>
			
					<!-- answerStatus를 출력 -->
					<td>${answerStatus}</td>
					<!-- <td><a
						href="${pageContext.request.contextPath}/inquiryboard/detail?in_no=${dto.in_no}">
							${dto.in_title}</a></td>
					-->
					<!-- in_set값이 y면 비밀번호 체크 아니면 바로 디테일 페이지로 넘어가게 조건걸기-->


					<c:choose>
						<c:when test="${!empty userID && !empty admin}">
							<td>
								<button type="button" class="btn btn-link" data-toggle="modal"
									data-target="#adminModal"
									onclick="openModal(${dto.in_no},'${dto.in_password}')">${dto.in_title}</button>
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<button type="button" class="btn btn-link" data-toggle="modal"
									data-target="#detailModal" onclick="openModalNo(${dto.in_no})">${dto.in_title}</button>
							</td>
						</c:otherwise>
					</c:choose>
					<td>${dto.fk_user_userID}</td>
					<td>
						<fmt:parseDate var="in_date" value="${dto.in_date}" pattern="yyyy-MM-dd"/>
		                <fmt:formatDate value="${in_date}" pattern="yyyy-MM-dd"/>
		            </td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr  class="text-center">
				<td colspan="5" >
					<ul class="pagination"  id="p1">
						<c:if test="${paging.bottomStart >= paging.bottomPageLimit }">
							<li><a
								href="${pageContext.request.contextPath}/inquiryboard?in_no=${(paging.bottomStart-2)*paging.onePageLimit}">이전</a></li>
						</c:if>
						<!-- 	1 2 3 4 5 6 7 8 9 10	 -->
						<c:forEach var="i" begin="${paging.bottomStart}"
							end="${paging.endStart}">
							<li <c:if test="${paging.bottomCurrent == i}"> class="active"</c:if>><a
								href="${pageContext.request.contextPath}/inquiryboard?in_no=${(i-1)*paging.onePageLimit}"
								title=""> ${i} </a></li>
						</c:forEach>
						<!-- 	다음		 -->
						<c:if test="${paging.endStart < paging.bottomPageAll }">
							<li><a
								href="${pageContext.request.contextPath}/inquiryboard?in_no=${(paging.endStart)*paging.bottomPageLimit}">다음</a></li>
						</c:if>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
	<div class="container">
		<!-- Trigger the modal with a button -->

		<!-- 아이디가 비어있지 않고 일반회원이라면 문의하기 -->
		<!-- 로그인시 String userAuthority = request.getParameter("userPassword"); 
			구현하려고 시도 userAuthority명칭
			${!empty userID && userAuthority == 0}-->
		<c:if test="${!empty userID && empty admin}">
			<div class="text-center">
				<button type="button" class="btn btn-info btn-lg"
					data-toggle="modal" data-target="#myModal">문의하기</button>
			</div>
			
		</c:if>
		<div class="text-center">
			<c:if test="${!empty userID && !empty admin}">
				<p class="btn btn-warning">답변하려면 제목을 누르세요</p>
			</c:if>
		</div>
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- writeModal-->
				<!-- writeModal-->
				<!-- writeModal-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>

					</div>
					<div class="modal-body">
						<form
							action="${pageContext.request.contextPath}/inquiryboard/write"
							method="post" id="writeform">
							<fieldset>
								<legend>1:1질문하기</legend>
								<div class="form-group">
									<label for="in_title">제목</label> <input type="text"
										id="in_title" name="in_title" class="form-control">
								</div>
								<div class="form-group">
									<label for="userID">작성자</label><input type="text" id="userID"
										name="userID" value="${userID}" class="form-control"
										readonly="readonly">

								</div>
								<div class="form-group">
									<label for="in_password">비밀번호</label> <input type="password"
										id="in_password" name="in_password" class="form-control">
								</div>
								<div class="form-group">
									<label for="in_password_check">비밀번호확인</label> <input
										type="password" id="in_password_check"
										name="in_password_check" class="form-control">
								</div>
								<div class="form-group">
									<label for="in_content">내용</label>
									<textarea rows="10" cols="60" name="in_content" id="in_content"
										class="form-control"></textarea>
								</div>
								<div class="form-group text-right">
									<div class="modal-footer">
										<input type="submit" value="입력" class="btn btn-info">

										<button type="button" class="btn btn-danger"
											data-dismiss="modal">취소</button>

										<a href="${pageContext.request.contextPath}/inquiryboard"
											class="btn btn-warning">목록보기</a>
									</div>
								</div>
							</fieldset>
						</form>
						<!-- writeModal-->
						<!-- writeModal-->
						<!-- writeModal-->
						<script>
							
						</script>
					</div>

				</div>

			</div>
		</div>

	</div>
	<div class="container">
		<!-- Trigger the modal with a button -->


		<!-- detail Modal -->
		<!-- detail Modal -->
		<div class="modal fade" id="detailModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="text-center">
							<h4 class="modal-title">본인글 확인하기</h4>
						</div>
					</div>
					<form
						action="${pageContext.request.contextPath}/inquiryboard/detail"
						method="get" id="d-detail">
						<div class="modal-body">
							<label>비밀번호</label> <input type="password" name="in_password"
								class="form-control">
						</div>
						<div class="modal-footer">
							<input type="hidden" id="modalInNo" name="in_no"
								value="${param.in_no}"> <input type="submit"
								value="확인하기" class="btn btn-info">
							<button type="button" class="btn btn-danger" data-dismiss="modal">취소하기</button>
						</div>
					</form>

				</div>

			</div>
		</div>

	</div>
	<!-- detail Modal -->
	<!-- detail Modal -->

	<div class="container">
		<!-- Trigger the modal with a button -->


		<!-- admin Modal -->
		<!-- admin Modal -->
		<div class="modal fade" id="adminModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<div class="text-center">
							<h4 class="modal-title">관리자 답변페이지</h4>
						</div>
					</div>
					<form
						action="${pageContext.request.contextPath}/inquiryboard/detail"
						method="get" id="a-deail">
						<div class="modal-footer">
							<input type="hidden" id="modal_InNo" name="in_no"
								value="${param.in_no}"> <input type="hidden"
								id="modalIn_Pass" name="in_password"
								value="${param.in_password}"> <input type="submit"
								value="이동하기" class="btn btn-info">
							<button type="button" class="btn btn-danger" data-dismiss="modal">취소하기</button>
						</div>
					</form>

				</div>

			</div>
		</div>

	</div>
	<!-- admin Modal -->
	<!-- admin Modal -->

</div>
<script>
  function openModalNo(in_no) {
    document.getElementById("modalInNo").value = in_no;

  }
  </script>
<script>
  function openModal(in_no, in_password) {
    document.getElementById("modal_InNo").value = in_no;
    document.getElementById("modalIn_Pass").value = in_password;
   
  }
</script>
<script>
	$(document).ready(function(){
					$("#writeform").submit(function(){
						if( $("#in_title").val() == "") {
							alert("제목을 입력하세요.");
							$("#in_title").focus();
							return false;
						}
						if( $("#in_password").val() == "") {
							alert("비밀번호를 입력하세요.");
							$("#in_password").focus();
							return false;
						}
						if( $("#in_password_check").val() == "") {
							alert("비밀번호를 입력하세요.");
							$("#in_password_check").focus();
							return false;
						}
						if ($("#in_password").val() != $("#in_password_check").val()) {
							alert("비밀번호가 일치하지 않습니다. 확인해주세요.");
							$("#in_password").val("");
							$("#in_password_check").val("");
							$("#in_password").focus();
							return false;
						}
						if( $("#in_content").val() == "") {
							alert("내용을 입력하세요");
							$("#in_content").focus();
							return false;
						}
					});
				});
	</script>

<%@ include file="/inc/footer.jsp"%>