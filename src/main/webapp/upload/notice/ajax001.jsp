
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container panel panel-info">
		<h3 class="panel-heading">AJAX</h3>
		<ol>
			<li>서버에 요청</li>
			<li>서버에 데이터처리(XML, CSV , JSON)</li>
			<li>요청한 데이터를 일부분만 바꾸기</li>
			<li>※ 데이터요청이 비동기 처리(다른작업 가능)</li>
		</ol>
	</div>
	<!-- 			-->
	<!--  			-->
	<!--  			-->
	<div class="container panel panel-info">
		<h3 class="panel-heading">[1] AJAX-EX : #text1 버튼을 클릭하면  .r1 영역에 본인이름 출력</h3>
	
	<pre> [data] - [text1.txt] 홍길동</pre>
	<p><input type="button" value="text1" class="btn btn-danger" name="text1" id="text1" /> </p>
	<div class="result r1"></div>
		<script>
		$(function(){
			$("#text1").on("click",function(){
				$.ajax({
					url : "data/text1.txt" ,
					type : "get" , dataType : "text" ,
					success:function(data){ $(".r1").html(data);  } ,
					error : function( xhr , textStatus , errorThrown ){
						$(".r1").html(textStatu + ("http-" + xhr.status + "/" + errorThrown));
					}
				});
			});
		});
		</script>
	</div>
	
	<div class="container panel panel-info">
		<h3 class="panel-heading">[2] AJAX-EX : #text3 버튼을 클릭하면  .resultr2 영역에 text2.txt 파일불러오기</h3>
	<pre> 
		  CHOCO MILK
		  src="images/choco.jpg" alt=""/>
	</pre>
	<p><input type="button" value="text2" class="btn btn-danger" name="text2" id="text2" /> </p>
	<div class="result r2"></div>
		<script>
		$(function(){
			$("#text2").on("click",function(){
				$.ajax({
					url : "data/text2.txt" ,
					type : "get" , dataType : "text" ,
					success:function(data){ $(".r2").html(data);  } ,
					error : function( xhr , textStatus , errorThrown ){
						$(".r2").html(textStatu + ("http-" + xhr.status + "/" + errorThrown));
					}
				});
			});
		});
		</script>
	</div>
	<!--    /Ajax_Basic001     -->
	<div class="container panel panel-info">
		<h3 class="panel-heading">[3] AJAX-EX : #text3 버튼을 클릭하면  .r3 영역에 Ajax_Basic001 출력</h3>
	<pre> 
		  db연동하기
	</pre>
	<p><input type="text" value="홍길동" name="yourname" id="yourname" /> </p>
	<p><input type="button" value="DB-연동" class="btn btn-danger" name="text3" id="text3" /> </p>
	<div class="result r3"></div>
		<script>
		$(function(){
			$("#text3").on("click",function() {
				$.ajax({
					url : "/data/Ajax_Basic001" ,
					type : "get" , dataType : "text" ,
					data : {"query" : $("#yourname").val() } ,
					success:function(data){ $(".r3").html(data);  } ,
					error : function( xhr , textStatus , errorThrown ){
						$(".r3").html(textStatus + ("http-" + xhr.status + "/" + errorThrown));
					}
				});
			});
		});
		</script>
	</div>




</body>
</html>	