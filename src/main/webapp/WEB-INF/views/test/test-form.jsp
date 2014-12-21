<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   

<h1>New Test: </h1>
 
<form:form action="${action}" commandName="test" modelAttribute="test" method="POST">

	<form:errors path="*" cssClass="errorblock" element="div" />
	
	<form:input path="name" /> <form:errors path="name" cssClass="error" /> <br/>
	
	<input type="submit" value="Save" />
	<input type="reset" value="Cancel" onclick="window.history.back();"/>
  
</form:form> 