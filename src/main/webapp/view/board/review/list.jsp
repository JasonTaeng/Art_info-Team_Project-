<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>

	<div class="container" style="margin-top: 5%; min-height: 500px">
    <h3>공연후기 게시판</h3>
    <form action="${pageContext.request.contextPath}/view/board/review/write.jsp" method="post" id="form">
    <table class="table table-scripted">
        <thead>
                <c:forEach var="l" items="${list}" varStatus="status">
                <div>
                <img class="img1" alt="이미지" src="${pageContext.request.contextPath}/images/user.png">
                <p class="p1"> 
                <a href="${pageContext.request.contextPath}/reviewboard/detail?re_no=${l.re_no}">${l.re_title}</a> 
                <br/>
                    ${l.re_no}/ ${l.fk_user_userID} ${l.re_date} 조회수: ${l.re_hit} </p>
                </div>
               </c:forEach>
        </thead>
      <tfoot>
	<tr>
		<td colspan="5" class="text-center">
			<ul class="pagination">
			
			<c:if test="${bottomStart > 1}"> 
				<li><a href="${pageContext.request.contextPath}/reviewboard?pageNo=${bottomStart - 1}&pageSize=10"
				class="btn btn-danger">이전</a></li>
			</c:if>	
			
        <c:forEach var="i" begin="${bottomStart}" end="${endStart}">
            <li <c:if test="${bottomCurrent == i}">class="active"</c:if>>
                <a href="${pageContext.request.contextPath}/reviewboard?pageNo=${i}&pageSize=10" title="">${i}</a>
            </li>
        </c:forEach>
        
	<c:if test="${endStart != totalPages}">
	<li><a href="${pageContext.request.contextPath}/reviewboard?pageNo=${endStart + 1}&pageSize=10"
		class="btn btn-danger">다음</a></li>
	</c:if>
			
			</ul>
		</td>
	</tr>
</tfoot>
    </table>
    <!--  사용자만 글쓰기 가능  -->
    <c:choose>
    	<c:when test="${empty admin && !empty userID}">
    <p class="text-right">
        <input type="submit" value="글쓰기" class="btn btn-danger">
    </p>
    	</c:when>
    </c:choose>
    </form>
</div>
<script>
	$(".img1").css("width", "3%");
	$(".p1").css("margin", "-37px 0px 7px 42px");
</script>

<%@ include file="/inc/footer.jsp" %>
    
    
    
    
    
     <%--   	<tbody>
				<c:forEach var="dto" items="${paging.list10}" varStatus="status">
					<tr>
						<td>${paging.pageTotal - paging.pstartno - status.index}</td>
					</tr>
				</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" class="text-center">
							<ul class="pagination">
							 <c:if test="${paging.bottomStart >= paging.bottomPageLimit}"> 
								<li><a href="${pageContext.request.contextPath}/paging?pstartno=${(paging.bottomStart-2)*paging.onePageLimit}"
								class="btn btn-danger">이전</a></li>
							</c:if>	 
								<!--  이전(90)  11(100) -->
								<c:forEach var="i" begin="${paging.bottomStart}" end="${paging.endStart}">
								
								<li  <c:if test="${paging.bottomCurrent == i}">  class="active"   </c:if>><a
								
									href="${pageContext.request.contextPath}/paging?pstartno=${(i-1)*paging.onePageLimit}"
									title=""> 
									${i}
									
									</a></li>
										
								</c:forEach>
								<!--     다음    전체페이지 26 / 10 -->
								<c:if test="${paging.endStart < paging.bottomPageAll}">
								<li><a href="${pageContext.request.contextPath}/paging?pstartno=${(paging.endStart)*paging.bottomPageLimit}"
								class="btn btn-danger">다음</a></li>
							</c:if>	
							</ul>
						</td>
					</tr>
				</tfoot> --%>
 