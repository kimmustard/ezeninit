<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.cmtContainer{
margin: 30px;
margin-left: 300px;
margin-right: 300px;
}
.badgeText{
	text-align: justify;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<table class="table table-hover">
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
			<tr>
				<td>작성일</td>
				<td>${bvo.regAt }</td>
			</tr>
			<tr>
				<td>수정일</td>
				<td>${bvo.modAt }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${bvo.readCount }</td>
			</tr>
	</table>
	
	<a href="/board/modify?bno=${bvo.bno }"><button type="button">수정</button></a><br>
	<a href="/board/remove?bno=${bvo.bno }"><button type="button">삭제</button></a><br>
	<a href="/"> 메인으로 </a>
	<hr>
	
	<!-- 댓글 입력라인 -->
	<div class="cmtContainer">
		<div class="input-group mb-3">
			<span class="badge bg-primary" id="cmtWriter">댓글테스터</span>
     		<input type="text" class="form-control" placeholder="댓글을 입력해주세요." id="cmtText">
     		<button class="btn btn-primary" type="button" id="cmtPostBtn">댓글 작성</button>
    	</div>	 	
	</div>
	
	<hr>
	
	<!-- 댓글 표시 라인 -->
	
	<ul id="cmtListArea">
	
	
		
	</ul>
	
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<jsp:include page="../common/footer.jsp" />
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
</body>
</html>