<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap/bootstrap-theme.css"/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/layout.css"/>" />
	
	<script src="<c:url value="/resources/js/jquery.js" />"></script>
	<script src="<c:url value="/resources/js/json2.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script src="<c:url value="/resources/js/main.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="row" >
			<c:if test="${not empty user}">
				<div class="col-md-3">
					<tiles:insertAttribute name="menu" />
				</div>
				<div class="col-md-9 well">
					<tiles:insertAttribute name="body" />
				</div>
			</c:if>
			
			<c:if test="${empty user}">
				<div class="col-md-4"></div>
				<div class="col-md-4 well">
					<tiles:insertAttribute name="body" />
				</div>
				<div class="col-md-4"></div>
			</c:if>
			
		</div>
	</div>
	<div class="container">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>