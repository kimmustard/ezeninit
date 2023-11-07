<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<html>

<head>
<title>Home</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=4ebr9nmpg6"></script>
<style type="text/css">

body{
	overflow-x: hidden;
}
.mainContainer {
	width: 4000px;
	display: flex;
}

.profile {
	background-color: #2c3e50;
	width: 300px;
	text-align: center;	
	position: relative;
	z-index: 1;
}

.boardContainer {
	width: 3000px;
	height: 800px;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

.boardBox {
	width: 500px;
	background-color: yellow;
	margin-left: 15px;
	margin-right: 15px;
}

.boardBody {
	height: 500px;
}
.another-bi{
	font-size: 30px;
}

.bi-body{
	display: flex;
	justify-content: space-around;
	align-items: center;	
}

.list-span{
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	display: inline-block;
	width: 180px;
}

.list-span2{
	text-overflow: ellipsis;
	white-space: nowrap;
	overflow: hidden;
	display: inline-block;
	width: 100px;
}

.card-header{
	font-weight: 700;
}

td>a:link,td>a:visited {
	text-decoration: none;
	color: #18bc9c;
	font-weight: 600;
}
.non-user-btn{
	position: absolute;
	top: 50%;
	left: 50%;
	transform:translate(-50%, -50%);
	z-index: 2;
}

.non-user{
	position:absolute;
	background-color: rgba(0, 0, 0, 0.3);
	border-radius: 5px;
	width: 100%;
	height: 100%;
	z-index: 1;
}

.login-btn{
	width: 180px;
	height: 50px;
	border: 1px solid red;
}
.slider-btn{
	z-index: 1;
}
.right-btn{
	position: absolute;
	top: 450px;
	font-size: 40px;
	left: 1830px;
	border: none;
	background-color:transparent;
}

.left-btn{
	position: absolute;	
	top: 450px;
	font-size: 40px;
	left: 350px;
	border: none;
	background-color:transparent;
}
.bi-chevron-double-left{
	color: #2c3e50;
	font-size: 60px;
	font-weight: 700;
}

.bi-chevron-double-right{
	color: #2c3e50;
	font-size: 60px;
	font-weight: 700;
}

.bi:hover {
	color: #18bc9c;
}


</style>
</head>

<body>

	<jsp:include page="common/header.jsp" />
	<jsp:include page="common/nav.jsp" />

	<div class="mainContainer">
		<sec:authorize access="isAnonymous()">
		<div class="card mb-3 profile">
		<div class="non-user-btn"><a href="/member/login"><button type="button" class="btn btn-success login-btn">로그인</button></a></div>
		<div class="non-user"></div>
			<h3 class="card-header">Profile</h3>
			<div class="card-body">
				<h5 class="card-title">아이디정보</h5>
				<h6 class="card-subtitle text-muted">닉네임</h6>
			</div>
			<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size: 1.125rem; text-anchor: middle">
			    <rect width="100%" height="100%" fill="#868e96"></rect>
			    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
			 </svg>
			<div class="card-body">
				<p class="card-text">자기소개</p>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">회원등급</li>
				<li class="list-group-item">가입날짜</li>
				<li class="list-group-item">마지막 로그인</li>
			</ul>
			<div class="card-body bi-body">
				<a href="https://github.com/" target="_blank" class="card-link"><i class="bi bi-github another-bi"></i></a>
				<a href="https://twitter.com/" target="_blank" class="card-link"><i class="bi bi-twitter another-bi"></i></a>
				<a href="https://www.facebook.com/" target="_blank" class="card-link"><i class="bi bi-facebook another-bi"></i></a>
				<a href="https://www.google.com/" target="_blank" class="card-link"><i class="bi bi-google another-bi"></i></a>
			</div>
			<div class="card-footer text-muted"> 출석일 : n 일</div>
		</div>
		</sec:authorize>
		
		
		<sec:authorize access="isAuthenticated()">
		<sec:authentication property="principal.mvo.email" var="authEmail"/>
		<sec:authentication property="principal.mvo.nickName" var="authNick"/>
		<sec:authentication property="principal.mvo.authList" var="auths"/>
		<sec:authentication property="principal.mvo.regAt" var="authReg"/>
		<sec:authentication property="principal.mvo.lastLogin" var="authLast"/>
		<div class="card mb-3 profile">
			<h3 class="card-header">Profile</h3>
			<div class="card-body">
				<h5 class="card-title">아이디 : ${authEmail}</h5>
				<h6 class="card-subtitle text-muted">닉네임 : ${authNick}</h6>
			</div>
			<svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size: 1.125rem; text-anchor: middle">
			    <rect width="100%" height="100%" fill="#868e96"></rect>
			    <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
			 </svg>
			<div class="card-body">
				<p class="card-text">자기소개</p>
			</div>
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<span>회원등급</span><br>
					<c:choose>
						<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
							<span class="badge bg-primary">관리자</span>
						</c:when>
						<c:otherwise>
							<span class="badge bg-success">일반</span>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="list-group-item">
				<span>가입일</span><br>
					${authReg}
				</li>
				<li class="list-group-item">
				<span>마지막 로그인</span><br>
					${authLast}
				</li>
			</ul>
			<div class="card-body bi-body">
				<a href="https://github.com/" target="_blank" class="card-link"><i class="bi bi-github another-bi"></i></a>
				<a href="https://twitter.com/" target="_blank" class="card-link"><i class="bi bi-twitter another-bi"></i></a>
				<a href="https://www.facebook.com/" target="_blank" class="card-link"><i class="bi bi-facebook another-bi"></i></a>
				<a href="https://www.google.com/" target="_blank" class="card-link"><i class="bi bi-google another-bi"></i></a>
			</div>
			<div class="card-footer text-muted"> 출석일 : n 일</div>
		</div>
		</sec:authorize>


		<div class="slider-btn">
			<button class="left-btn" id="leftBtn"><i class="bi bi-chevron-double-left"></i></button>
			<button class="right-btn" id="rightBtn"><i class="bi bi-chevron-double-right"></i></button>
		</div>
		<div class="boardContainer" id="boardContainer">
			<div class="card border-primary mb-3 boardBox">
				<div class="card-header">공지사항</div>
				<div class="card-body boardBody">
					<!-- 공지목록 -->
					<table class="table table-hover">
						<tr>
							<td>번호</td>
							<td>글제목</td>
							<td>작성자</td>
						</tr>
						<c:forEach items="${noticeList }" var="noticeList">
						<tr>
							<td><a href="/board/detail?bno=${noticeList.bno }"><span>${noticeList.bno}</span></a></td>
							<td class="icon-line"><c:if test="${noticeList.hasFile > 0 }"><i class="bi bi-image"></i></c:if><a href="/board/detail?bno=${noticeList.bno }"><span class="list-span">${noticeList.title}</span></a></td>
							<td><span class="list-span2">${noticeList.writer}</span></td>
						</tr>
						</c:forEach>
					</table>	
				</div>
			</div>

			<div class="card border-primary mb-3 boardBox">
					<div class="card-header">내가 쓴 글</div>
				<div class="card-body boardBody">
					<table class="table table-hover">
						<tr>
							<td>번호</td>
							<td>글제목</td>
							<td>작성자</td>
						</tr>
						<c:forEach items="${myList }" var="myList">
						<tr>
							<td><a href="/board/detail?bno=${myList.bno }"><span>${myList.bno}</span></a></td>
							<td class="icon-line"><c:if test="${myList.hasFile > 0 }"><i class="bi bi-image"></i></c:if><a href="/board/detail?bno=${myList.bno }"><span class="list-span">${myList.title}</span></a></td>
							<td><span class="list-span2">${myList.writer}</span></td>
						</tr>
						</c:forEach>
					</table>	
				</div>
			</div>

			<div class="card border-primary mb-3 boardBox">
				<div class="card-header">최신 글 목록</div>
				<div class="card-body boardBody">
					<table class="table table-hover">
						<tr>
							<td>번호</td>
							<td>글제목</td>
							<td>작성자</td>
						</tr>
						<c:forEach items="${newList }" var="newList">
						<tr>
							<td><a href="/board/detail?bno=${newList.bno }"><span>${newList.bno}</span></a></td>
							<td class="icon-line"><c:if test="${newList.hasFile > 0 }"><i class="bi bi-image"></i></c:if><a href="/board/detail?bno=${newList.bno }"><span class="list-span">${newList.title}</span></a></td>
							<td><span class="list-span2">${newList.writer}</span></td>
						</tr>
						</c:forEach>
					</table>	
				</div>
			</div>
			
			<div class="card border-primary mb-3 boardBox">
				<div class="card-header">고객센터</div>
				<div class="card-body boardBody">
					준비중
				</div>
			</div>
			
			<div class="card border-primary mb-3 boardBox">
				<div class="card-header">찾아오는 길</div>
				<div class="card-body boardBody">
					<div>
						<div id="map" style="width:400px;height:400px;"></div>
						<!-- <img alt="그림없음" src="/resources/image/지도.PNG" width="400" height="400"> -->
					</div>
				</div>
			</div>
			
			<div class="card border-primary mb-3 boardBox">
				<div class="card-header">날씨</div>
				<div class="card-body boardBody">
					준비중
				</div>
			</div>
			
		

		</div>
		
		

	</div>

	<jsp:include page="common/footer.jsp" />
	
	<script type="text/javascript" src="/resources/js/mainSlider.js"></script>
	<script>
	var map = {
	    center: new naver.maps.LatLng(37.450384844128415, 126.70303420707847),
	    zoom: 17
	    
	};
	var map = new naver.maps.Map('map', map);
	
	var marker = new naver.maps.Marker({
	    position: new naver.maps.LatLng(37.450384844128415, 126.70303420707847),
	    map: map
	});

	</script>

</body>

</html>