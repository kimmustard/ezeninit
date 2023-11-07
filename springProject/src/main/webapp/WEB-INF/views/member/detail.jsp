<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<style type="text/css">
	.errorFont{
		color: red;
		font-weight: 700;
	}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
		
		<form action="/member/detail" method="post">
			<table class="table table-hover">
				<tr>
					<th>이메일</th>
					<td><input type="text" value="${mvo.email }" name="email" readonly="readonly"> </td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" value="" name="pwd"> </td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><input type="text" value="${mvo.nickName }" name="nickName"> </td>
				</tr>
				<tr>
					<th>권한목록</th>
					<td>
						<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.mvo.authList" var="auths"/>
						<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
							<span class="badge bg-primary">관리자</span>
						</c:if>
						<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_USER')).get() }">
							<span class="badge bg-success">일반</span>
						</c:if>
						</sec:authorize>
					</td>
				</tr>
			</table>
			<button type="submit"> 수정완료 </button>
		</form>
			<a href="/member/remove?email=${mvo.email }"><button type="button">회원탈퇴</button></a>
			<a href="/"><button> 돌아가기 </button></a>
	<jsp:include page="../common/footer.jsp"/>
	
	<script type="text/javascript">
		const isMod = `<c:out value="${isMod}" />`;
		if(isMod == 1){
			alert('수정완료!');
		}
	</script>
</body>
</html>