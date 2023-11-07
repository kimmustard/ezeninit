<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<title>Insert title here</title>
<style>
	.container-fluid{
		margin: 10px;
		display: inline;
	}
	.navbar-collapse{
		font-size: 20px;
		margin-left: 350px;
	}
	.navbar-brand{
		font-size: 20px;
		font-weight: 700;
	}
	.nav-item{
		margin-left: 20px;
		margin-right: 20px;
	}
	.navbar-title{
		font-size: 30px;
		margin-left: 70px;
	}

</style>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
			<div class="container-fluid">
				<a class="navbar-brand navbar-title" href="#">E-ZEN</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarColor01">
					<ul class="navbar-nav me-auto">
						<li class="nav-item">
							<a class="nav-link active" href="/">Home
								<span class="visually-hidden">(current)</span>
							</a>
						</li>

					
						<li class="nav-item">
							<a class="nav-link" href="/board/noticeList">공지사항</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/list">게시판</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/board/company">회사정보</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="#">고객센터</a>
						</li>
						
						<!-- 게스트에게만 보임 -->
						<sec:authorize access="isAnonymous()">
						<li class="nav-item">
							<a class="nav-link" href="/member/login">로그인</a>
						</li>
						</sec:authorize>
						
						
						<sec:authorize access="isAuthenticated()">
							<sec:authentication property="principal.mvo.email" var="authEmail"/>
							<sec:authentication property="principal.mvo.nickName" var="authNick"/>
							<sec:authentication property="principal.mvo.authList" var="auths"/>
						<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_USER')).get() }">
							<li class="nav-item">
								<a class="nav-link" href="/member/detail?email=${authEmail}">${authNick}(${authEmail})</a>
							</li>
						</c:if>
						<li class="nav-item">
							<a class="nav-link" href="" id="logoutLink">로그아웃</a>
						</li>
						<form action="/member/logout" method="post" id="logoutForm">
							<input type="hidden" name="email" value="${authEmail}">
						</form>
						
						<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
							<li class="nav-item">
								<a class="nav-link" href="/member/list">관리자 페이지</a>
							</li>
						</c:if>
						</sec:authorize>
					</ul>
					
				</div>
			</div>
		</nav>


	</header>
	<script type="text/javascript">
		document.getElementById('logoutLink').addEventListener('click', (e)=>{
			e.preventDefault();
			document.getElementById('logoutForm').submit();
		})
	</script>
</body>
</html>