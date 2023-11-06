<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.container-fluid, .pagination{
	display: flex;
	justify-content: center;
}

.searchBtn {
	width: 350px;
}
.listContainer{
	display: flex;
	justify-content: center;
}
.listbox{
	width: 1000px;
}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<div class="listContainer">
		<div class="listbox">
	<!-- 검색 라인 -->
	<div class="container-fluid">
		<form action="/board/list" method="get" class="d-flex" role="search">
			<c:set value="${ph.pgvo.type }" var="typed" />
			<div>
				<select class="form-select" name="type" id="inputGroupSelect02">
					<option ${typed == null ? 'selected' : '' }>Choose...</option>
					<option value="t" ${typed eq 't' ? 'selected' : '' }>Title</option>
					<option value="w" ${typed eq 'w' ? 'selected' : '' }>Writer</option>
					<option value="c" ${typed eq 'c' ? 'selected' : '' }>Content</option>
					<option value="tw" ${typed eq 'tw' ? 'selected' : '' }>Title or Writer</option>
					<option value="tc" ${typed eq 'tc' ? 'selected' : '' }>Title or Content</option>
					<option value="cw" ${typed eq 'cw' ? 'selected' : '' }>Content or Writer</option>
					<option value="twc" ${typed eq 'twc' ? 'selected' : '' }>ALL</option>
				</select>
			</div>
			<div class="searchBtn">
				<input class="form-control me-2" type="search" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search" aria-label="Search">
			</div>
			<input type="hidden" name="pageNo" value="1">
			<input type="hidden" name="qty" value="${ph.pgvo.qty}">
			<button class="btn btn-primary position-relative" type="submit">
				Search <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					${ph.totalCount } 
					<span class="visually-hidden">unread messages</span>
				</span>
			</button>
		</form>
	</div>
	<table class="table table-hover">
		<thead>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>댓글수</td>
				<td>파일수</td>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list }" var="bvo">
				<tr>
					<td><a href="/board/cntdetail?bno=${bvo.bno }">${bvo.bno }</a></td>
					<td><a href="/board/cntdetail?bno=${bvo.bno }">${bvo.title }</a></td>
					<td>${bvo.writer }</td>
					<td>${bvo.regAt }</td>
					<td>${bvo.readCount }</td>
					<td>${bvo.cmtQty }</td>
					<td>${bvo.hasFile }</td>
				</tr>

			</c:forEach>
		</tbody>

	</table>

	<!-- 페이징 라인 -->
	<div>
		<nav aria-label="Page navigation example">
			<ul class="pagination">
				<!-- 이전버튼 -->

				<li class="page-item ${(ph.prev eq false) ? 'disabled' : '' }">
					<a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous"> 
					<span aria-hidden="true">&laquo;</span>
				</a>
				</li>
				<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
					<li class="page-item">
						<a class="page-link" href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i}</a>
					</li>
				</c:forEach>
				<!-- 다음버튼 -->
				<li class="page-item ${(ph.next eq false) ? 'disabled' : '' }">
					<a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next"> 
					<span aria-hidden="true">&raquo;</span>
				</a>
				</li>
			</ul>
		</nav>
		

	</div>



	</div>
</div>
	<script type="text/javascript">
		const isOk = `<c:out value="${isOk}" />`;
		console.log(isOk);
		if (isOk == 1) {
			alert('삭제완료!');
		}
	</script>
	
	<jsp:include page="../common/footer.jsp" />
</body>
</html>