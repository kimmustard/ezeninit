<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	<div>
	
	<form:form action="/member/register" method="post" modelAttribute="mvo">
	<c:set var="emailError"><form:errors class="errorFont" path="email" /></c:set>
	<c:set var="pwdError"><form:errors class="errorFont" path="pwd" /></c:set>
	<c:set var="nameError"><form:errors class="errorFont" path="nickName" /></c:set>
		<div class="form-group">
		
			<label for="exampleInputEmail1" class="form-label mt-4">Email address</label> 
			<c:if test="${empty emailError }">
			<form:input type="text" path="email" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="아이디" /> 
			<form:errors class="errorFont" path="email"></form:errors>
			</c:if>
			<c:if test="${not empty emailError }">
			<form:input type="text" path="email" class="form-control is-invalid" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="아이디" /> 
			<form:errors class="errorFont" path="email"></form:errors>
			</c:if>
		</div>

		<div class="form-group">
			
			<label for="exampleInputPassword1" class="form-label mt-4">Password</label>
			<c:if test="${empty pwdError }">
			<form:input type="password" path="pwd" class="form-control" name="pwd" id="exampleInputPassword1" placeholder="비밀번호" autocomplete="off" />
			<form:errors class="errorFont" path="pwd"></form:errors>
			</c:if>
			<c:if test="${not empty pwdError }">
			<form:input type="password" path="pwd" class="form-control is-invalid" name="pwd" id="exampleInputPassword1" placeholder="비밀번호" autocomplete="off" />
			<form:errors class="errorFont" path="pwd"></form:errors>
			</c:if>
		</div>
	
		
		<div class="form-group">
			<label for="exampleInputPassword1" class="form-label mt-4">Name</label> 
			<c:if test="${empty nameError }">
			<form:input type="text" path="nickName" class="form-control" name="nickName" id="exampleInputPassword1" placeholder="닉네임" autocomplete="off" />
			<form:errors class="errorFont" path="nickName"></form:errors>
			</c:if>
			<c:if test="${not empty nameError }">
			<form:input type="text" path="nickName" class="form-control is-invalid" name="nickName" id="exampleInputPassword1" placeholder="닉네임" autocomplete="off" />
			<form:errors class="errorFont" path="nickName"></form:errors>
			</c:if>
			
			
		</div>
		
		<button type="submit">가입 작성완료</button>
	</form:form>

	</div>
	
	<jsp:include page="../common/footer.jsp"/>

</body>
</html>