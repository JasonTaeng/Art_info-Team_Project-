<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=75bc52b7e552dc0c2e818096144eaeeb"></script>
<%@ include file="/inc/header.jsp"%>

<div class="container text-center" style="min-height: 500px">
	<h3><b>제목 : ${dto.art_title}</b></h3>
	<p><b>등록일 :</b>
	<fmt:parseDate var="art_reg_date" value="${dto.art_reg_date}" pattern="yyyy-MM-dd"/>
	<fmt:formatDate value="${art_reg_date}" pattern="yyyy년 MM월 dd일"/></p>
	
	<div class="panel">
		<div class="form-group">
			${dto.art_content}
			<div class="text-center">
				<img src="${pageContext.request.contextPath}/upload/art-info/${dto.art_img_name}" alt="이미지">
			</div>
		</div>
		<p>
			<fmt:parseDate var="start_date" value="${dto.start_date}" pattern="yyyy-MM-dd"/>
			<b>공연시간 : </b><fmt:formatDate value="${start_date}" pattern="yyyy-MM-dd"/>&nbsp;8:30PM ~&nbsp;
			<fmt:parseDate var="end_date" value="${dto.end_date}" pattern="yyyy-MM-dd"/>
			<fmt:formatDate value="${end_date}" pattern="yyyy-MM-dd"/>&nbsp;8PM
		</p>
		<p><b>공연장소 : </b>${dto.art_location}</p>
		<p><b>관람연령 : </b>${dto.art_age}</p>
		<p><b>가격 : </b><fmt:formatNumber value="${dto.art_price}" pattern="#,##0"/>원</p>
	</div>
	<div class="text-right">
		<c:choose>
		<c:when test="${!empty admin}">
			<a href="${pageContext.request.contextPath}/art_info/edit?art_no=${dto.art_no}" class="btn btn-danger">수정</a> 
			<a href="${pageContext.request.contextPath}/art_info/delete?art_no=${dto.art_no}" class="btn btn-danger">삭제</a> 
		</c:when>
		</c:choose>
		<a href="${pageContext.request.contextPath}/art_info" class="btn btn-info">목록보기</a>
	</div><br><br>
	<div id="staticMap" style="width:49%; height:500px; position: center;"></div>

	<script>
	$(document).ready(function() {
		var art_no = ${dto.art_no};
		var marker = "";
		var pos_x = 0;
		var pos_y = 0;
		
		// 이미지 지도에 표시할 마커입니다
		if(art_no==1) {
			pos_x = 37.5523164; pos_y = 126.9189686;
			marker = {
			    position: new kakao.maps.LatLng(pos_x, pos_y), 
			    text: '서울 마포구 잔다리로 44 1층 (클럽온에어)' // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다
			};
		} else if(art_no == 2) {
			pos_x = 37.51924321283553; pos_y = 127.12739985977699;
			marker = {
				position: new kakao.maps.LatLng(37.51924321283553, 127.12739985977699), 
				text: 'KSPO돔(올림픽 체조경기장)' 
				};
		} else if(art_no == 3) {
			pos_x = 37.51924321283553; pos_y = 127.12739985977699;
			marker = {
				    position: new kakao.maps.LatLng(37.51924321283553, 127.12739985977699), 
				    text: 'KSPO돔' 
				};
		} else if(art_no == 4) {
			pos_x = 37.407234733121946; pos_y = 126.63497436345395;
			marker = {
				    position: new kakao.maps.LatLng( 37.407234733121946, 126.63497436345395), 
				    text: '인천 송도 달빛 축제 공원)' 
				};
		} else if(art_no == 5) {
			pos_x = 37.664562083817444; pos_y = 126.74180872488981;
			marker = {
				    position: new kakao.maps.LatLng(37.664562083817444, 126.74180872488981 ), 
				    text: '킨텍스 제2전시장' 
				};
		} else if(art_no == 6) {
			pos_x = 35.16856448185326; pos_y = 128.06222851044066;
			marker = {
				    position: new kakao.maps.LatLng(35.16856448185326, 128.06222851044066), 
				    text: '도쿄 지바 마린 스타디움' 
				};
		} else if(art_no == 7) {
			pos_x = 35.487131484007264; pos_y = 126.9747255628878;
			marker = {
				    position: new kakao.maps.LatLng(35.487131484007264, 126.9747255628878), 
				    text: '나가타현 나에바 리조트 ' 
				};
		} else if(art_no == 10) {
			pos_x = 37.5523164; pos_y = 126.9189686;
			marker = {
				    position: new kakao.maps.LatLng(37.5523164, 126.9189686), 
				    text: '서울 마포구 잔다리로 44 1층 (클럽온에어)' 
				};
		}
		
		var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div
	    staticMapOption = { 
	        center: new kakao.maps.LatLng(pos_x, pos_y), // 이미지 지도의 중심좌표
	        level: 2, // 이미지 지도의 확대 레벨
	        marker: marker // 이미지 지도에 표시할 마커
	    };

		// 이미지 지도를 생성합니다
		var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
		
		
	});
	

	</script>
</div><br><br><br><br>


<script>
	$("div #staticMap").css({
		'margin': '0px 8px 3px 283px',
	});
	$("div img").css({
		'width': '550px',
		'height': '700px'
	});
	$("img").css('background-position', 'center');
	$(document).ready(function() {
		$(".img1").css("width", "3%");
		$(".p1").css("margin", "-37px 0px 7px 42px");
	});
	</script>

</body>
</html>