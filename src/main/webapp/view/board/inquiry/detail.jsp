<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp"%>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/inquiry.css?ver=1">
	<div class="container  detail">
	<h3 class="">1:1질문</h3>
	<div class="form-group">
		<label for="in_title">제목</label> <span id="in_title"
			class="form-control">${dto.in_title}</span>
	</div>
	<div class="form-group">
		<label for="userID">작성자</label> <span id="userID" class="form-control">${dto.fk_user_userID}</span>
	</div>
	<div class="form-group my-mb">
		<label for="in_content">내용</label> <span id="in_content"
			class="form-control">${dto.in_content}</span>
	</div>
	<!-- 여기에 추가 답변이 있다면 답변 나오게 하기 -->
	<c:set var="reply" value="${Rdto}"></c:set>
	<c:choose>
		<c:when test="${!empty Rdto.in_reply_content}">
			<div class="form-group">
				<label for="in_reply_content">답변내용</label> <span
					id="in_reply_content" class="form-control">${Rdto.in_reply_content}</span>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
	<div class="text-right">

		<c:choose>
			<c:when test="${!empty userID && empty admin}">
				<!--		수정하기 확인 모달	 -->
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#editModal" onclick="openModal_edit(${dto.in_no})">수정</button>
				<!--		삭제하기 확인 모달	 -->
				<button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#deleteModal" onclick="openModal(${dto.in_no})">삭제</button>
			</c:when>
			<c:when
				test="${!empty userID && !empty admin && !empty Rdto.in_reply_content }">
				<a class="btn btn-info"
					href="${pageContext.request.contextPath}/view/board/inquiryReply/edit.jsp?in_reply_no=${dto.in_no}&&in_reply_content=${Rdto.in_reply_content}">답변수정하기</a>
				<button type="button" class="btn btn-danger" data-toggle="modal"
					data-target="#openModalreply_delete" onclick="openModalreply_delete(${dto.in_no})">답변삭제하기</button>

				<button class="btn btn-warning" onclick="history.go(-1)">취소하기</button>
			</c:when>
			<c:when
				test="${!empty userID && !empty admin && empty Rdto.in_reply_content }">
				<div class="text-right">

					<a class="btn btn-info"
						href="${pageContext.request.contextPath}/view/board/inquiryReply/write.jsp?in_reply_no=${dto.in_no}">답변하기</a>
					<button class="btn btn-danger" onclick="history.go(-1)">취소하기</button>
				</div>
			</c:when>
			<c:otherwise>
				
				
			</c:otherwise>
		</c:choose>
	</div>
</div>
<!-- reply_deletelModal -->
<!-- reply_deletelModal -->
<div class="modal fade" id="openModalreply_delete" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="text-center">
					<h4 class="modal-title">삭제하시겠습니까?</h4>
				</div>
			</div>
			<form action="${pageContext.request.contextPath}/inquiryReply/delete"
				method="get" id="">
				<div class="modal-footer">
					<input type="hidden" id="in_reply_no" name="in_reply_no"
						value="${param.in_no}"> <input type="submit" value="확인하기"
						class="btn btn-info">
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소하기</button>
				</div>
			</form>
		</div>

	</div>
	<!-- reply_deletelModal -->
	<!-- reply_deletelModal -->
</div>

<!-- editModal -->
<!-- editModal -->
<div class="modal fade" id="editModal" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="text-center">
					<h4 class="modal-title">본인글 확인하기</h4>
				</div>
			</div>
			<form action="${pageContext.request.contextPath}/inquiryboard/editView"
				method="get" id="">
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="in_password"
						class="form-control">
				</div>
				<div class="modal-footer">
					<input type="hidden" id="modalInNo_edit" name="in_no"
						value="${param.in_no}"> <input type="submit" value="확인하기"
						class="btn btn-info">
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소하기</button>
				</div>
			</form>
		</div>

	</div>
	<!-- editModal -->
	<!-- editModal -->
</div>



<!-- deletelModal -->
<!-- deletelModal -->
<div class="modal fade" id="deleteModal" role="dialog">
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
				action="${pageContext.request.contextPath}/inquiryboard/delete"
				method="get" id="delete">
				<div class="modal-body">
					<label>비밀번호</label> <input type="text" name="in_password"
						class="form-control">
				</div>
				<div class="modal-footer">
					<input type="hidden" id="modal_d_InNo" name="in_no"
						value="${param.in_no}"> <input type="submit" value="확인하기"
						class="btn btn-info">
					<button type="button" class="btn btn-danger" data-dismiss="modal">취소하기</button>
				</div>
			</form>

		</div>

	</div>
	<!-- deletelModal -->
	<!-- deletelModal -->
</div>


<!-- editModal -->
<script>
 		 function openModal_edit(in_no) {
    	document.getElementById("modalInNo_edit").value = in_no;
  	}
	</script>
<!-- editModal -->

<!-- deleteModal -->
<script>
 		 function openModal(in_no) {
    	document.getElementById("modal_d_InNo").value = in_no;
  	}
	</script>
<!-- deleteModal -->
<!-- reply_deleteModal -->
<script>
 		 function openModalreply_delete(in_no) {
    	document.getElementById("in_reply_no").value = in_no;
  	}
	</script>
<!-- reply_deleteModal -->
<%@ include file="/inc/footer.jsp"%>