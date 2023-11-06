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
		</table>
			<button type="submit"> 수정완료 </button>
	</form>
			<a href="/member/remove?email=${mvo.email }"><button type="button">회원탈퇴</button></a>
			<a href="/board/list"><button> 돌아가기 </button></a>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>