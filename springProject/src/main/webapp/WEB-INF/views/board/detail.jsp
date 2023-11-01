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
			<tr>
				<td>글번호</td>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${bvo.writer }</td>
			</tr>
			<tr> 
				<td>글제목</td>
				<td>${bvo.title }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${bvo.content }</td>
			</tr>
	</table>
	
	<a href="/board/modify?bno=${bvo.bno }"><button type="button">수정</button></a><br>
	<a href="/board/remove?bno=${bvo.bno }"><button type="button">삭제</button></a><br>
	<a href="/"> 메인으로 </a>
	
</body>
</html>