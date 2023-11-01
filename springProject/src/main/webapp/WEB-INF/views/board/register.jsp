<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>Insert title here</title>
</head>
<body>
	
	<form action="/board/register" method="post">
	
	
		제목:	<input type="text" name="title"> <br>
		글쓴이: <input type="text" name="writer"> <br>
		내용:	<input type="text" name="content"> <br>
		
		<button type="submit"> 작성완료 </button> <br><br>
		<a href="/"><button type="button">메인으로</button></a>
	</form>
	
</body>
</html>