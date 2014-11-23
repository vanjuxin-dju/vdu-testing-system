<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/header.css"/>" />

<div class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/login"/>">Testing System</a>
			<c:if test="${not empty user}">
				<a class="navbar-brand" href="<c:url value="/subject/list" />">View Subjects</a>
			</c:if>
		</div>
		<ul class="nav navbar-nav navbar-right">
			<c:if test="${not empty user}">
				<li><a href="<c:url value="/profile/${user.id}" />">${user.name} (ID: ${user.id})</a></li>
				<li><a href="<c:url value="/profile/${user.id}" />">Profile</a></li>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</c:if>
			<c:if test="${empty user}">
				<li><a href="<c:url value="/login"/>">Log In</a></li>
			</c:if>
		</ul>
	</div>
</div>