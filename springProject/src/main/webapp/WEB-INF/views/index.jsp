<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<jsp:include page="common/header.jsp" />
	<jsp:include page="common/nav.jsp" />


<h1> 메인입니다. </h1>

<a href="/board/register">작성하기</a> <br>
<a href="/board/list">리스트로</a> <br>

	<jsp:include page="common/footer.jsp" />
</body>
</html>
