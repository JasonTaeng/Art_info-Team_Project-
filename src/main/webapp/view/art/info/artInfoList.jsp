<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/inc/header.jsp" %>
    
<style>
	ul, ol {
	    list-style: none;
	    padding: 15px 1px 15px 0px;
	}
	
	a {
	    text-decoration: none;
	    white-space: nowrap; /* 추가된 코드 */
	}
	
	#content {
	    display: flex;
	    width: 1000px;
	    margin: 0 auto;
	}
	
	aside {
	    color: white;
	    width: 130px;
	    height: 100%;
	    border: solid;
	    box-sizing: border-box;
	}
	form {
	    margin: 12px 363px -4px -27px;
	}
	
	.mygallery {
	    flex-grow: 1;
	    border: 1px solid;
	}
	
	.img1 {
	    margin-right: 10px;
	    width: 55%;
	}
	
	.container {
	    display: flex;
	    flex-direction: row;
	    justify-content: flex-start;
	    align-items: flex-start;
	    flex-wrap: wrap;
	}
	
	.container .item {
	    display: flex;
	    flex-direction: column;
	    justify-content: center;
	    align-items: center;
	    margin-right: 10px;
	}
	i.fas.fa-tshirt {
	    margin: 0px 13px 0 0px;
	}
</style>   
    <div class="text-center">
        <p>
        <c:choose>
        	<c:when test="${!empty admin}">
            	<a href="${pageContext.request.contextPath}/art_info/write" class="btn btn-primary right">글쓰기</a> 
            </c:when>
        </c:choose>
        </p>
    </div>
    
    <div id="content">
    	<!-- 검색 부분 -->
        <aside>
        	<form method="post" action="${pageContext.request.contextPath}/art_info">
            <ul class="category">
                <li>
                    <i class="fa fa-bars" aria-hidden="true">카테고리</i>
                </li>
                <li>
                    <a href="#">
                        <i class="fas fa-tshirt" aria-hidden="true"></i>공연종류
                        <i class="fas fa-angle-right" aria-hidden="true"></i>
                    </a>
                    <ol>
                        <li><a href="#">국내공연</a></li>
                        <li><a href="#">내한공연</a></li>
                        <li><a href="#">페스티벌</a></li>
                        <li><a href="#">해외공연</a></li>
                   </ol>
                </li>
            </ul>
            </form>
        </aside>
        
           <div class="col-sm-12 mygallery">
	  	    <br>
	  	    <c:forEach  var="dto"  items="${list}"  varStatus="status">
	  	    <div class="col-sm-3">
		  		<p class="imgbox"><img class="img" src="${pageContext.request.contextPath}/upload/art-info/${dto.art_img_name}" alt="${dto.art_title}"
		  			onClick="location.href = '${pageContext.request.contextPath}/art_info/detail?art_no=${dto.art_no}'" style="cursor:pointer;"></p>
		  		<p><b>제목:</b> ${dto.art_title}</p>
		  		<p><b>장소:</b> ${dto.art_location}</p>
				<p><b>가격 : </b><fmt:formatNumber value="${dto.art_price}" pattern="#,##0"/>원</p>
	  	    </div>
	  	    </c:forEach>
  		</div>
   </div>
   
   

<!-- 
<div class="container">
		<div class="row">
			<form method="post" name="search" action="${pageContext.request.contextPath}/art_info/search.jsp">
				<table class="pull-right">
					<tr>
						<td><select class="form-control" name="searchField">
								<option value="0">선택</option>
								<option value="art_title">제목</option>
								<option value="userID">작성자</option>
						</select></td>
						<td><input type="text" class="form-control"
							placeholder="검색어 입력" name="searchText" maxlength="100"></td>
						<td><button type="submit" class="btn btn-success">검색</button></td>
					</tr>

				</table>
			</form>
		</div>
	</div>
-->

<script>
	$(".mygallery").css({
		'width': '500px',
	    'box-sizing': 'content-box',
	    'border': '3px solid',
	    'border-radius': '10px',
		'background-color': '#faebd7'
	});
	$(".col-sm-3").css('height', '350px');
	$(".modalimg img").css('width', '50%');
	$(".imgbox img").css({
		'width': '150px',
		'height': '180px',
		'overflow': 'hidden'
	});
</script>

</body>
</html>