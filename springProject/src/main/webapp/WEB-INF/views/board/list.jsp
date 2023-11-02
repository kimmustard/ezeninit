<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<style type="text/css">
	.form-group{
		display: flex;
		width: 500px;
	}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />


	

	<table class="table table-hover">
		<thead>
			<tr>
				<td>번호</td>
				<td>글제목</td>
				<td>작성자</td>
				<td>내용</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>댓글수</td>
				<td>파일수</td>
			</tr>


		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.bno}</a></td>
					<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title}</a></td>
					<td>${bvo.writer}</td>
					<td>${bvo.content}</td>
					<td>${bvo.regAt }</td>
					<td>${bvo.readCount }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.hasFile }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<!-- 검색 라인 -->
	<div>
		<form action="/board/list" method="get">
			<div></div>
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>


				<div class="form-group">
					<div class="input-group mb-3">
					<select class="form-select" name="type" id="exampleSelect1">
						<option ${typed == null ? 'selected' : '' }> 검색목록 </option>
						<option value="t" ${typed eq 't' ? 'selected' : '' }> 제목 </option>
						<option value="w" ${typed eq 'w' ? 'selected' : '' }> 작성자 </option>
						<option value="c" ${typed eq 'c' ? 'selected' : '' }> 내용 </option>
						<option value="tw" ${typed eq 'tw' ? 'selected' : '' }> 제목+작성자 </option>
						<option value="tc" ${typed eq 'tc' ? 'selected' : '' }> 제목+내용 </option>
						<option value="wc" ${typed eq 'wc' ? 'selected' : '' }> 작성자+내용 </option>
						<option value="twc" ${typed eq 'twc' ? 'selected' : '' }> 전체 </option>
					</select> 
						<input type="text" class="form-control" name="keyword" value="${ph.pgvo.keyword }" placeholder="검색어 입력">
						<input type="hidden" name="pageNo" value="1">
						<input type="hidden" name="qty" value="${ph.pgvo.qty }">
						<button class="btn btn-primary" id="button-addon2" type="submit"> 검색 </button>
					</div>
				</div>
		</form>
	</div>
	<br>
	
	<!-- 페이징 라인 -->
	<ul class="pagination">
		
		<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
			<a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
				<span aria-hidden="true">&laquo;</span>
			</a>
		</li>
		
		<!-- 페이지 숫자 -->
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		<li class="page-item">
			<a class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
		</li>
		</c:forEach>
		
		<!-- next -->
		
		<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
			<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">
				<span aria-hidden="true">&raquo;</span>
			</a>
		</li>
		
	</ul>
	<hr>

	<a href="/"><button type="button">메인으로</button></a>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>