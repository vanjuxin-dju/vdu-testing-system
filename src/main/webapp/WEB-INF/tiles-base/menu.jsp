<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not empty user}">
	<ul class="nav nav-pills nav-stacked">
		<li class="active"><a href="<c:url value="/subject/list" />"> Subjects List</a></li>
	</ul>
</c:if>