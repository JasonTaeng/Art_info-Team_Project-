<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>

	<div class="container">
		<a href="${pageContext.request.contextPath}/admin/adminjoin" class="btn btn-primary pull-left">일반 관리자 등록</a>
		<div id="div-flex" class="row">
			<form id="form1" action="${pageContext.request.contextPath}/admin/search" method="post">
			    <label for="userEnabled">조건을 선택하세요</label>
			    <select name="userEnabled" id="userEnabled">
			    	<option disabled>=상태 조회=</option>
			    	<option value="10" ${E==10? "selected": ""}>전체</option>
				    <option value="1" ${E==1? "selected": ""}>가입</option>
				    <option value="0" ${E==0? "selected": ""}>탈퇴</option>
				    <option value="-1" ${E==-1? "selected": ""}>제명</option>
				    <option value="-7" ${E==-7? "selected": ""}>7일 정지</option>
				    <option value="-30" ${E==-30? "selected": ""}>30일 정지</option>
			    </select>
			    <select name="userAuthority" id="userAuthority">
			    	<option disabled>=등급 조회=</option>
			    	<option value="10" ${A==10? "selected": ""}>전체</option>
			    	<option value="2" ${A==2? "selected": ""}>메인 관리자</option>
			    	<option value="1" ${A==1? "selected": ""}>일반 관리자</option>
				    <option value="0" ${A==0? "selected": ""}>회원</option>
			    </select>
			    <input type="submit" value="검색">
			</form>
			
			<form id="form2" action="${pageContext.request.contextPath}/admin/update" method="post">
				<input type="hidden" name="userID" value="">
			    <select name="updateEnabled" id="updateEnabled">
			    	<option disabled selected>=상태 변경=</option>
				    <option value="1">가입</option>
				    <option value="-1">제명</option>
				    <option value="-7">7일 이용정지</option>
				    <option value="-30">30일 이용정지</option>
			    </select>
			    <input type="submit" class="btn btn-danger btn-sm" value="변경">
			</form>
			
			<form id="form3" action="${pageContext.request.contextPath}/admin/update" method="post">
				<input type="hidden" name="userID" value="">
			    <select name="updateAuthority" id="updateAuthority">
			    	<option disabled selected>=등급 변경=</option>
			    	<option value="2">메인 관리자</option>
			    	<option value="1">일반 관리자</option>
				    <option value="0">회원</option>
			    </select>
			    <input type="submit" class="btn btn-danger btn-sm" value="변경">
			</form>
		</div>
		<br>
		
		<div class="row">
			<table class="table table-striped table-hover" style="text-align: center; border: 1px solid #dddddd">
				
				<thead>
					<tr>
						<th scope="col"><input id="checkAll" type="checkbox"></th>
						<th scope="col">번호</th>
						<th scope="col">ID</th>
						<th scope="col">이름</th>
						<th scope="col">성별</th>
						<th scope="col">이메일</th>
						<th scope="col">전화번호</th>
						<th scope="col">가입일자</th>
						<th scope="col">상태</th>
						<th scope="col">등급</th>
					</tr>
				</thead>
				
				<tbody>
				<!--페이지 넘버에 따라 받아온 리스트 출력-->
				<c:set var="page" value="${param.page}"/>
				<c:set var="startPageNum" value="${page-(page-1)%5}"/>
				<c:set var="lastPageNum" value="${Math.ceil(lastNum/10)}"/>
				<c:forEach var="u" items="${list}" varStatus="status">
					<tr onClick="location.href = '${pageContext.request.contextPath}/admin/detail?userID=${u.userID}'" title="유저 정보로 이동합니다." style="cursor:pointer;">
                    	<td>
                    		<input class="checkbox" type="checkbox" value="${u.userID}">
                    	</td>
	                    <td>
	                    	${lastNum - status.index - (page-1)*10}
	                    </td>
	                    <td>
		                    <c:if test="${u.userAuthority==2}">
		                    	<img class="adminImg" src="${pageContext.request.contextPath}/images/mainAdmin.png">
		                    </c:if>
		                    <c:if test="${u.userAuthority==1}">
		                    	<img class="adminImg" src="${pageContext.request.contextPath}/images/generalAdmin.png">
		                    </c:if>
	                    	<strong>${u.userID}</strong>
	                    </td>
	                    <td>${u.userName}</td>
	                    <td>${u.userGender}</td>
	                    <td>${u.userEmail}</td>
						<td>${u.userPhoneNum}</td>
	                    <td>
	                    	<fmt:parseDate var="userDate" value="${u.userDate}" pattern="yyyy-MM-dd"/>
	                    	<fmt:formatDate value="${userDate}" pattern="yyyy-MM-dd"/>
	                    </td>
	                    <td>
	                    	<c:if test="${u.userEnabled==1}">가입</c:if>
		                    <c:if test="${u.userEnabled==0}"><span style="color: gray;">탈퇴</span></c:if>
		                    <c:if test="${u.userEnabled==-7}"><span style="color: orange;"><b>7일 이용정지</b></span></c:if>
		                    <c:if test="${u.userEnabled==-30}"><span style="color: orange;">30일 이용정지</span></c:if>
		                    <c:if test="${u.userEnabled==-1}"><span style="color: red;"><b>제명</b></span></c:if>
	                    </td>
	                    <td>
	                    	<c:if test="${u.userAuthority==2}"><span style="color: blue;"><b>메인 관리자</b></span></c:if>
		                    <c:if test="${u.userAuthority==1}"><b>일반 관리자</b></c:if>
		                    <c:if test="${u.userAuthority==0}">회원</c:if>
	                    </td>
                    </tr>
				</c:forEach>
                </tbody>
            </table>
		</div>
		
		<!-- 페이저 처리 -->
		<div class="text-center">
			<ul class="list-inline pagination">
				<c:if test="${startPageNum>1}">
				<c:if test="${empty param.E}">
					<li><a class="btn btn-prev" href="${pageContext.request.contextPath}/admin?page=${startPageNum-5}">이전</a></li>
				</c:if>
				<c:if test="${!empty param.E}">
					<li><a class="btn btn-prev" href="${pageContext.request.contextPath}/admin?page=${startPageNum-5}&E=${E}&A${A}">이전</a></li>
				</c:if>
				</c:if>
				<c:if test="${startPageNum==1}">
					<li><span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span></li>
				</c:if>
				
				<c:forEach var="i" begin="0" end="${(startPageNum+4<=lastPageNum)? 4 : lastPageNum-startPageNum}">
					<li ${page==(startPageNum+i)? ' class="active" ' : ''}>
						<c:if test="${empty param.E}">
							<a class="-text- orange bold" href="${pageContext.request.contextPath}/admin?page=${startPageNum+i}">
								${startPageNum+i}
							</a>
						</c:if>
						<c:if test="${!empty param.E}">
							<a class="-text- orange bold" href="${pageContext.request.contextPath}/admin?page=${startPageNum+i}&E=${E}&A${A}">
								${startPageNum+i}
							</a>
						</c:if>
					</li>
				</c:forEach>
				
				<c:if test="${startPageNum+4<lastPageNum}">
				<c:if test="${empty param.E}">
					<li><a class="btn btn-next" href="${pageContext.request.contextPath}/admin?page=${startPageNum+5}">다음</a></li>
				</c:if>
				<c:if test="${!empty param.E}">
					<li><a class="btn btn-next" href="${pageContext.request.contextPath}/admin?page=${startPageNum+5}&E=${E}&A${A}">다음</a></li>
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
		// 관리자 이미지 크기 조절
		$(".adminImg").css({
			'width': '30px',
			'height': '30px'
		});
		// form 3개 수평 정렬
		$("#form1").css({
			'display': 'inline-block',
			'width': '460px'
		});
		$("#form2, #form3").css({
			'display': 'inline-block',
			'width': '180px',
			'padding': '0'
		});
		$("#div-flex").css({
			'display': 'flex',
			'justify-content': 'flex-end'
		});
		$(document).ready(function() {
			// 체크박스 전체 선택
			$("#checkAll").click(function() {
				$(".checkbox").click();
			});
			// 체크 박스 이벤트 핸들러(테이블 행 이벤트 전파 막기, 클릭한 유저아이디 배열에 넣고 form2,form3 input type hidden에 전달)
			$(".checkbox").click(function(event) {
			    event.stopPropagation();
			    var userIDArray = [];
			    $(".checkbox:checked").each(function() {
			    	userIDArray.push($(this).val());
			    });
			    $("#form2 input[name='userID']").val(userIDArray.join(','));
			    $("#form3 input[name='userID']").val(userIDArray.join(','));
			});
			// form1 둘 중 하나의 조건 검색해야 form 제출할 수 있게 alert, form 제출 막기
			$("#form1").submit(function(){
				if($("#userEnabled").val()==null && $("#userAuthority").val()==null) {
					alert('둘 중 하나의 조건은 선택해야 합니다.');
					return false;
				}
			});
			// form2, form3 제출 전 체크박스와 변경 조건 선택 안 했으면 alert, form 제출 막기
			$("#form2").submit(function() {
				if($("#form2 input[name=userID]").val()=="") {
					alert('체크박스를 선택해주세요.');
					return false;
				} else if($("#form2 > select[name=updateEnabled]").val()==null) {
					alert('변경할 상태를 선택해주세요.');
					return false;
				}
			});
			$("#form3").submit(function() {
				if($("#form3 input[name=userID]").val()=="") {
					alert('체크박스를 선택해주세요.');
					return false;
				} else if($("#form3 > select[name=updateAuthority]").val()==null) {
					alert('변경할 등급을 선택해주세요.');
					return false;
				}
			});
		});
	</script>
</body>
</html>