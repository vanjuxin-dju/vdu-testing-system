<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<h1>New Client:</h1>

<form:form action="${action}" commandName="subject"
	modelAttribute="subject" method="POST">

	<form:label path="name">Name:</form:label>
	<form:input path="name" />
	<br />
	
	<input type="submit" value="Save" />
	<input type="reset" value="Cancel" />

</form:form>
