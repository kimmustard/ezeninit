<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>회사 정보</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4ebr9nmpg6"></script>
	<style type="text/css">
	.mapContainer{
		display: flex;
		margin: 50px;
		width: 100%;
		height: 1000px;
	}
	
	.mapText{
		margin: 50px;
	}
	</style>
</head>
<body>

	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<div class="mapContainer">
		<!-- 지도 div -->
		<div id="map" style="width:1000px;height:600px;">
		</div>
		
		<div class="mapText">
		테스트입니다.
		</div>
	</div>
	

	
	
	<jsp:include page="../common/footer.jsp" />
	<script>
	var map = {
	    center: new naver.maps.LatLng(37.450384844128415, 126.70303420707847),
	    zoom: 18
	    
	};
	var map = new naver.maps.Map('map', map);
	
	var marker = new naver.maps.Marker({
	    position: new naver.maps.LatLng(37.450384844128415, 126.70303420707847),
	    map: map
	});

	</script>
</body>
</html>