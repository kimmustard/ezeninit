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

	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>글제목</td>
				<td>작성자</td>
				<td>내용</td>
			</tr>
		
		
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.bno}</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.content}</td>
				</tr>
			</c:forEach>
		</tbody>	
	</table>
	
	<a href="/"><button type="button"> 메인으로 </button></a>
</body>
</html>