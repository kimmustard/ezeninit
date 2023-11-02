<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.errorFont{
	color: red;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form:form action="/board/modify" method="post" modelAttribute="bvo">
			<table class="table table-hover">
				<tr>
					<td>글번호</td>
					<td><input type="text" value="${bvo.bno }" name="bno" readonly="readonly"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" value="${bvo.writer }" name="writer" readonly="readonly"></td>
				</tr>
				<tr> 
					<td>글제목</td>
					<td><form:input type="text" path="title" value="${bvo.title }" name="title" /> <form:errors class="errorFont" path="title"></form:errors>
					</td>
						
					
				</tr>
				<tr>
					<td>내용</td>
					<td><form:textarea type="text" path="content" value="${bvo.content }" name="content" rows="3" /> <form:errors class="errorFont" path="content"></form:errors></td>
				</tr>
			</table>
		<button type="submit"> 수정하기 </button> <br>
		<a href="/board/list"> 리스트로 </a> <br>
		<a href="/"> 메인으로 </a>
	</form:form>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>