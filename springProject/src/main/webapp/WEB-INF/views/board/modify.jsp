<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
		<form:form action="/board/modify" method="post" modelAttribute="bvo" enctype="multipart/form-data">
		<table class="table table-hover">
			<tr>
				<th>글번호</th>
				<td>
					<input type="text" name="bno" value="${bvo.bno }" readonly="readonly">	
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<form:input type="text" path="title" class="form-control" value="${bvo.title }" name="title" id="exampleFormControlInput1" />
					<form:errors class="errorFont" path="title"></form:errors>
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${bvo.writer }" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td> 
				<form:textarea class="form-control" path="content" name="content" value="${bvo.content }" id="exampleFormControlTextarea1" rows="3" />
				<form:errors class="errorFont" path="content"></form:errors>
				</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><form:hidden path="regAt"/>${bvo.regAt }</td>
			</tr>
			<tr>	
				<th>조회수</th>
				<td><form:hidden path="readCount"/>${bvo.readCount }</td>
			</tr>
		
		
		</table>
		
		<!-- 수정페이지에서 파일업로드 -->
		<div class="mb-3">
			<input type="file" class="form-control" name="files" value="" id="files" style="display:none;" multiple="multiple">
			<!-- input button 트리거용 -->
			<button type="button" id="trigger" class="btn btn-outline-primary">파일 업로드</button>
		</div>
		
		<div class="mb-3" id="fileZone">
			<!-- 첨부파일 표시 구역 -->
			
		</div>
		
		<hr>
		
		<!-- 그림 라인 -->
		<c:set value="${boardDTO.flist}" var="flist"></c:set>
		<ul>
		<c:forEach items="${flist }" var="fvo">
				
			<li>
				<c:choose>
					<c:when test="${fvo.fileType > 0}">
						<div>
							<div class="fw-bold">${fvo.fileName }</div>
						 	<img alt="그림 없음" src="/upload/${fn:replace(fvo.saveDir,'\\','/')}/${fvo.uuid}_th_${fvo.fileName}">	
							<div class="badge rounded-pill text-bg-dark">${fvo.regAt } </div>	
							<button type="button" class="file-x btn btn-danger" data-uuid="${fvo.uuid }"> 삭제 </button>				
						</div>
					</c:when>
				<c:otherwise>
						<div>
							<!-- file 아이콘 같은 모양 값으로 넣을 수 있음. -->
						</div>
				</c:otherwise>
				</c:choose>
			</li>
		
		
		</c:forEach>
		</ul>
	
	<button type="submit" id="regBtn" class="btn btn-primary"> 수정 완료 </button><br>

</form:form>
	<br>
	<a href="/"><button type="button" class="btn btn-primary"> 메인으로 </button></a>
	
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<jsp:include page="../common/footer.jsp" />

</body>
</html>