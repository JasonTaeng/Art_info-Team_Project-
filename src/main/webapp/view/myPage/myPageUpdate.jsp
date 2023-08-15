<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/inc/header.jsp" %>

<c:set var="u" value="${User}"/>

<div class="container">
	<div class="row">
    <form id="form" method="post" action="${pageContext.request.contextPath}/mypage/update">
        <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
        	<thead>
            	<tr>
                	<th colspan="2" style="background-color:#eeeeee; text-align:center;">내 정보 수정하기</th>
                </tr>
            </thead>
            <tbody>
            	<tr>
                	<td style="width:30%;">아이디</td>
                    <td><input type="text" class="form-control" placeholder="아이디" name="userID" value="${u.userID}" disabled></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input id="${u.userPassword}" type="text" class="form-control" placeholder="비밀번호" name="userPassword" value="${u.userPassword}"></td>
                </tr>
                <tr>
                	<td>이름</td>
                    <td><input type="text" class="form-control" name="userID" value="${u.userName}" disabled></td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td><input type="text" class="form-control" placeholder="성별" name="userGender" value="${u.userGender}" disabled></td>
                </tr>
                <tr>
                    <td>이메일</td>
                    <td><input id="${u.userEmail}" type="text" class="form-control" placeholder="이메일" name="userEmail" maxlength="50" value="${u.userEmail}" ></td>
                </tr>
                <tr>
                    <td>휴대폰 번호</td>
                    <td><input id="${u.userPhoneNum}" type="text" class="form-control" placeholder="폰 번호" name="userPhoneNum" value="${u.userPhoneNum}"></td>
                </tr>
                <tr>
                    <td>가입일자</td>
                    
                    <td>
                    	<fmt:parseDate var="userDate" value="${u.userDate}" pattern="yyyy-MM-dd"/>
		                <fmt:formatDate var="Date" value="${userDate}" pattern="yyyy년 MM월 dd일"/>
                    	<input type="text" class="form-control" placeholder="가입날짜" name="userDate" value="${Date}" disabled>
                    </td>
                </tr>
             </tbody>
         </table>
         <div class="text-center">
         	<input id="submit" type="submit" class="btn btn-primary" value="내 정보 수정">
         </div>
    </form>
    </div>
 </div>
<script>
	$(document).ready(function() {
		$("#submit").click(function() {
			if( $("input[name='userPassword']").val() == $("input[name='userPassword']").attr('id') &&
				$("input[name='userEmail']").val() == $("input[name='userEmail']").attr('id') &&
				$("input[name='userPhoneNum']").val() == $("input[name='userPhoneNum']").attr('id') ) {
					alert("최소 한 개를 수정해주세요.");
					return false;
			}
			if( $("input[name='userPassword']").val() == "" || $("input[name='userEmail']").val() == "" ||
				$("input[name='userEmail']").val() == "" )	{
					alert("공백을 제출할 수 없습니다.");
					return false;
			}
		});
	});
</script>
</body>
</html>