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
	                   <fmt:parseDate var="userDate" value="${u.userDate}" pattern="yyyy-MM-dd" />
		               <fmt:formatDate value="${userDate}" pattern="yyyy-MM-dd"/><!-- 가입시간도 -->
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
</body>
</html>
