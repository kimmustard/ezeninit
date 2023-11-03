<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
			
			<!-- 파일업로드 버튼  -->
		<div class="form-group">
	      	<input class="form-control" type="file" name="files" id="files" style="display:none;" multiple="multiple">
	      	<button type="button" id="trigger" class="btn btn-primary">파일 업로드</button>
    	</div>
		
		
		<!-- 첨부파일 표시구역  -->
		<div class="mb-3" id="fileZone">
			
		</div>
		
		<hr>
		
		<!-- 그림 라인 -->
		<c:set value="${bdto.flist}" var="flist"></c:set>
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
			
			
		<button type="submit" id="regBtn"> 수정하기 </button> <br>
		<a href="/board/list"> 리스트로 </a> <br>
		<a href="/"> 메인으로 </a>
	</form:form>
	
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}" />`
	</script>
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>