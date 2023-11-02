<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
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
	
	<form:form action="/board/register" method="post" modelAttribute="bvo">
	
		<table class="table table-hover">
		
			<tr>
				<td>제목</td>
				<td><form:input type="text" path="title" name="title" /> <form:errors class="errorFont" path="title" /></td>
			</tr>	
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><form:textarea path="content" name="content" rows="3" /> <form:errors class="errorFont" path="content" /></td>
			</tr>
		
		</table>
	
		<button type="submit"> 작성완료 </button> <br><br>
		<a href="/"><button type="button">메인으로</button></a>
	</form:form>
	
	<jsp:include page="../common/footer.jsp" />
	
</body>
</html>