<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
		<table class="table table-hover">
		<thead>
			<tr>
				<td>이메일</td>
				<td>닉네임</td>
				<td>가입날짜</td>
				<td>로그아웃시간</td>
				<td>탈퇴여부</td>
				<td>권한</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="mvo">
				<tr>
					<td>${mvo.email }</td>
					<td>${mvo.nickName }</td>
					<td>${mvo.regAt }</td>
					<td>${mvo.lastLogin }</td>
					<td>${mvo.isDel }</td>
					<td>
						<c:forEach items="${mvo.authList}" var="authList">
						
							<c:if test="${authList.auth eq 'ROLE_ADMIN'}">
							<span class="badge bg-primary">${authList.auth }</span>
							</c:if>
							
							<c:if test="${authList.auth eq 'ROLE_USER'}">
							<span class="badge bg-success">${authList.auth }</span>
							</c:if>
							
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>