<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg bg-body-tertiary">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Navbar</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#">Home</a></li>
						<li class="nav-item">
							<a class="nav-link" href="/board/list">Board List</a>
						</li>
						
						<!-- 현재 인증한 사용자의 정보를 가져와서 해당 권한이 있는 케이스를 open -->
						<!-- 사용자 정보는 principal -->
						<!-- admin 계정만 할 수 있는 일을 처리 -->
							<!-- 로그인 해야 open되는 메뉴들 -->
						<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.mvo.email" var="authEmail"/>
						<sec:authentication property="principal.mvo.nickName" var="authNick"/>
						<sec:authentication property="principal.mvo.authList" var="auths"/>
						
						<c:choose>
							<c:when test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
								<li class="nav-item">
									<a class="nav-link" href="/member/list"> ADMIN > ${authNick}(${authEmail})</a>
								</li>
								
							</c:when>
							<c:otherwise>
								<li class="nav-item">
									<a class="nav-link" href="/member/detail?email=${authEmail}">${authNick}(${authEmail})</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<li class="nav-item">
							<a class="nav-link" href="/board/register">Board Reg</a>
						</li>
						
			
						<li class="nav-item">
								<a class="nav-link" href="" id="logoutLink">logout</a>
						</li>
							<form action="/member/logout" method="post" id="logoutForm">
								<input type="hidden" name="email" value="${authEmail}">
							</form>
						</sec:authorize>
						
						<!-- 게스트에게만 보이는 버튼 -->
						<sec:authorize access="isAnonymous()">
							<li class="nav-item">
								<a class="nav-link" href="/member/register">Sign up</a>
							</li>
							
							<li class="nav-item">
								<a class="nav-link" href="/member/login">login</a>
							</li>
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