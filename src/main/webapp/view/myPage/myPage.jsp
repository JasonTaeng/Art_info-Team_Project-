<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>

<c:set var="u" value="${User}"/>

<div class="container">
    <div class="row">
        <table class="table" style="text-align:center; border:1px solid #dddddd">
           <thead>
               <tr>
                   <th colspan="3" style="background-color:#eeeeee; text-align:center;">유저 정보</th>
               </tr>
           </thead>
           <tbody>
               <tr>
                   <td style="width:30%;">아이디</td>
                   <td colspan="2">${u.userID}</td>
               </tr>
               <tr>
                   <td>비밀번호</td>
                   <td colspan="2">${u.userPassword}</td>
               </tr>
               <tr>
                   <td>이름</td>
                   <td colspan="2">${u.userName}</td>
               </tr>
               <tr>
                   <td>성별</td>
                   <td colspan="2">${u.userGender}</td>
               </tr>
                <tr>
                   <td>이메일</td>
                   <td colspan="2">${u.userEmail}</td>
               </tr>
               <tr>
                   <td>핸드폰 번호</td>
                   <td colspan="2">${u.userPhoneNum}</td>
               </tr>
               <tr>
                   <td>가입일자</td>
                   <td colspan="2">
	                   <fmt:parseDate value="${u.userDate}" pattern="yyyy-MM-dd" var="userDate" />
		               <fmt:formatDate value="${userDate}" pattern="yyyy년 MM월 dd일"/>
	               </td>
               </tr>
               <tr>
                	<td>상태</td>
               	    <td>
	               		<c:if test="${u.userEnabled==1}">가입</c:if>
	                    <c:if test="${u.userEnabled==0}"><span style="color: gray;">탈퇴</span></c:if>
	                    <c:if test="${u.userEnabled==-7}"><span style="color: orange;"><b>7일 이용정지</b></span></c:if>
	                    <c:if test="${u.userEnabled==-30}"><span style="color: orange;">30일 이용정지</span></c:if>
	                    <c:if test="${u.userEnabled==-1}"><span style="color: red;"><b>제명</b></span></c:if>
               	    </td>
               </tr>
               <tr>
               		<td>등급</td>
	           		<td>
		           		<c:if test="${u.userAuthority==2}"><span style="color: blue;"><b>메인 관리자</b></span></c:if>
	                    <c:if test="${u.userAuthority==1}"><b>일반 관리자</b></c:if>
	                    <c:if test="${u.userAuthority==0}">회원</c:if>
                    </td>
               </tr>
               <tr>
               	   <td>가입 IP</td>
                   <td colspan="2">${u.userJoinIP}</td>
               </tr>
               <tr>
               	   <td>최근 로그인 IP</td>
                   <td colspan="2">${u.userLastIP}</td>
               </tr>
           </tbody>
       </table>
	</div>
</div>

<div class="text-center">
    <a id="update-link" class="btn btn-warning pull-left" href="${pageContext.request.contextPath}/mypage/update">수정</a>
    <form id="delete-form" class="pull-right" action="${pageContext.request.contextPath}/mypage/delete" method="post">
	    <input type="hidden" name="userPassword" value="">
	    <input type="submit" class="btn btn-danger" value="탈퇴">
    </form>
</div>
<script>
	$(document).ready(function() {
		$("#update-link").css('margin-left','45%');
		$("#delete-form").css('margin-right','45%');
		$("#delete-form").submit(function() {
			var userPassword = prompt('비밀번호를 입력하세요.', '');
			if(userPassword==null || userPassword=="") {
				alert('비밀번호를 입력하세요.');
				return false;
			} else {
				$("#delete-form > input[type=hidden]").attr('value', userPassword);
			}
		});
	});
</script>
</body>
</html>
