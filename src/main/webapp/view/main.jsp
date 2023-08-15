<%@include file="/inc/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="main">
	<div>
	  	<h3 id="art-info-heading">공연 소식</h3>
		<div class="col-sm-12 mygallery">
	  	    <!-- 						 -->
	  	    <br>
	  	    <c:forEach  var="dto"  items="${list}"  varStatus="status">
	  	    <div class="col-sm-3">
		  		<p class="imgbox"><img class="img" src="${pageContext.request.contextPath}/upload/art-info/${dto.art_img_name}" alt="${dto.art_title}"    
		  			data-toggle="modal" data-target="#myModal${status.count}"></p>
		  		<p><b>제목:</b> ${dto.art_title}</p>
		  		<p><b>장소:</b> ${dto.art_location}</p>
				<p><b>가격 : </b><fmt:formatNumber value="${dto.art_price}" pattern="#,##0"/>원</p>
		  			
				<!-- Modal -->
				<div id="myModal${status.count}" class="modal fade" role="dialog">
					<div class="modal-dialog">
					    <!-- Modal content-->
					    <div class="modal-content">
					    	<div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal">&times;</button>
						        <h4 class="modal-title">${dto.art_title}</h4>
						    </div>
						    <div class="modal-body  modalimg">
						        <p><img src="${pageContext.request.contextPath}/upload/art-info/${dto.art_img_name}" alt="${dto.art_title}" /></p>
						        <p><b>제목:</b> ${dto.art_title}</p>
						  		<p><b>장소:</b> ${dto.art_location}</p>
						  		<p><b>가격 : </b><fmt:formatNumber value="${dto.art_price}" pattern="#,##0"/>원</p>
						    </div>
						    <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						    </div>
					    </div>
				    </div>
				</div><!-- Modal end --> 
	  	    </div><!-- col-6-Modal end --> 	
	  	    </c:forEach>
		 	<!-- 						 -->
		 	<!-- 						 -->
	  	</div> <!-- 	end col-sm-6 -->
	</div>
	<!--  <div class="form-control">
		<p  class="col-sm-6  more"><a href="${pageContext.request.contextPath}/art_info/write"
	 	    title="글쓰러가기"
	 	    class="btn btn-danger form-control">공연올리기</a></p>
	</div>
	-->
<div class="text-center">
	<p style="color:white;">HI</p>
	<h3>DATA 팀 프로젝트</h3>
	<p style="color:white;">HI</p>
	<br>
</div>
</div>

<script>
	$("#main").css('margin', '0 150px');
	$(".mygallery").css({
	    'box-sizing': 'content-box',
	    'border': '3px solid',
	    'border-radius': '10px',
		'background-color': '#faebd7'
	});
	$(".modalimg img").css('width', '50%');
	$(".imgbox img").css({
		'width': '150px',
		'height': '180px',
		'overflow': 'hidden'
	});
	$("#art-info-heading").css({
		'height': '48px',
	    'border-radius': '6px',
	    'text-align': 'center',
	    'box-sizing': 'border-box',
	    'padding-top': '11px',
	    'background-color': 'black',
	    'color': 'white'
	});
</script>
<%@include file="/inc/footer.jsp" %>
