<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Page</title>
<style type="text/css">
.errorFont{
	color: red;
}
</style>
</head>
<body>
	<sec:authentication property="principal.mvo.email" var="authEmail"/>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />
	<form:form action="/board/register" method="post" modelAttribute="bvo" enctype="multipart/form-data">
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">title</label>
			<form:input type="text" path="title" class="form-control" name="title" id="exampleFormControlInput1" />
			<form:errors class="errorFont" path="title"></form:errors>
		</div>
		<div class="mb-3">
			<label for="exampleFormControlInput1" class="form-label">writer</label>
			<input type="text" class="form-control" name="writer" value="${authEmail }" id="exampleFormControlInput1" readonly="readonly">
		</div>
		<div class="mb-3">
			<label for="exampleFormControlTextarea1" class="form-label">content</label>
			<form:textarea class="form-control" path="content" name="content" id="exampleFormControlTextarea1" rows="3" />
			<form:errors class="errorFont" path="content"></form:errors>
		</div>
		
		<div class="mb-3">
			<input type="file" class="form-control" name="files" value="" id="files" style="display:none;" multiple="multiple">
			<!-- input button 트리거용 -->
			<button type="button" id="trigger" class="btn btn-outline-primary">파일 업로드</button>
		</div>
		
		<div class="mb-3" id="fileZone">
			<!-- 첨부파일 표시 구역 -->
			
		</div>
		
		<button type="submit" class="btn btn-primary" id="regBtn"> 작성완료 </button>
		
	</form:form>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp" />
</body>
</html>