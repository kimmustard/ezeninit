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

	<form action="/board/modify" method="post">
			<table border="1">
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
					<td><input type="text" value="${bvo.title }" name="title"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" value="${bvo.content }" name="content"></td>
				</tr>
			</table>
		<button type="submit"> 수정하기 </button> <br>
		<a href="/board/list"> 리스트로 </a> <br>
		<a href="/"> 메인으로 </a>
	</form>
</body>
</html>