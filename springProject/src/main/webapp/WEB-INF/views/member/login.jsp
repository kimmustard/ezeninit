<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	<jsp:include page="../common/nav.jsp"/>
	
	<div>
		<form action="/member/login" method="post">
		<h4>로그인 입력</h4>
			<div class="form-group">
		      <label for="exampleInputEmail1" class="form-label mt-4">Email address</label>
		      <input type="text" class="form-control" name="email" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="아이디">
		      <small id="emailHelp" class="form-text text-muted">아이디를 이메일을 양식에 맞게 입력해주세요.</small>
		    </div>
		    
		    <div class="form-group">
		      <label for="exampleInputPassword1" class="form-label mt-4">Password</label>
		      <input type="password" class="form-control ${errText ne null ? 'is-invalid' : '' }" name="pwd" id="exampleInputPassword1" placeholder="비밀번호" autocomplete="off">
		    </div>
		    <c:if test="${not empty param.errMsg }">
		    	<div class="text-danger mb-3">
		    		<c:choose>
		    			<c:when test="${param.errMsg eq 'Bad credentials'}">
		    				<c:set var="errText" value="아이디/비밀번호가 일치하지 않습니다."/>
		    			</c:when>
		    			
		    			<c:otherwise>
		    				<c:set var="errText" value="관리자에게 문의해주세요."/>
		    			</c:otherwise>
		    		</c:choose>
		    		${errText }
		    	</div>
		    	
		    </c:if>
		    
		    <div>
			    <button type="submit">로그인</button>
			    <a href="/member/register"><button type="button">회원가입</button></a>
		    </div>
		</form>
	</div>
	
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>