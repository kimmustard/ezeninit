<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Register Page</title>
<style type="text/css">
.errorFont{
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
	
	<!-- email, password, nick name  -->
	<div class="container">
	
		<form:form action="/member/register" method="post" modelAttribute="mvo">
			<h4> Input your Information </h4>
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">이메일</label>
				<form:input type="text" path="email" class="form-control" name="email" id="exampleFormControlInput1" />
				<form:errors class="errorFont" path="email"></form:errors>
			</div>
			
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">비밀번호</label>
				<form:input type="text" path="pwd" class="form-control" name="pwd" id="exampleFormControlInput1" />
				<form:errors class="errorFont" path="pwd"></form:errors>
			</div>
			
			<div class="mb-3">
				<label for="exampleFormControlInput1" class="form-label">닉네임</label>
				<form:input type="text" path="nickName" class="form-control" name="nickName" id="exampleFormControlInput1" />
				<form:errors class="errorFont" path="nickName"></form:errors>
			</div>
			
			<button type="submit" class="btn btn-primary"> 가입완료 </button>
		</form:form>
		
	</div>
	
	<jsp:include page="../common/footer.jsp"/>
	
</body> 
</html>