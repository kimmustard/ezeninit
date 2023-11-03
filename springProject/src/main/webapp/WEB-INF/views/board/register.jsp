<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<head>
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.css" />
<style type="text/css">
.errorFont{
	color: red;
}
</style>

</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	
	<form:form action="/board/register" method="post" modelAttribute="bvo" enctype="multipart/form-data">
	
		<table class="table table-hover">
		
			<tr>
				<td>제목</td>
				<td>
					<form:input type="text" path="title" name="title"/>
					<form:errors class="errorFont" path="title" ></form:errors>
				</td>
			</tr>	
			<tr>
				<td>글쓴이</td>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<form:textarea path="content" name="content" rows="3" />
					<form:errors class="errorFont" path="content"></form:errors>
				</td>
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
	
		<button type="submit" id="regBtn" class="btn btn-primary"> 작성완료 </button> <br>
	</form:form>
		<a href="/"><button type="button" class="btn btn-primary">메인으로</button></a>
	
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<jsp:include page="../common/footer.jsp" />
</body>
</html>