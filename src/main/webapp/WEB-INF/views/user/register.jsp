<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<h3>Register form</h3>

<c:if test="${not empty error}">
	<div class="danger">
		<p class="alert alert-danger">${error}</p>
	</div>
</c:if>

<form name='loginForm' action="register" method='POST' class="form-horizontal" role="form">
	<div class="form-group">
		<label for="username" class="col-sm-4 control-label">User:</label>
		<div class="col-sm-8">
			<input type="text" id="username" name="username" class="form-control" placeholder="Enter user name">
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-4 control-label">Password:</label>
		<div class="col-sm-8">
			<input type="password" id="password" name="password" class="form-control" placeholder="Enter password">
		</div>
	</div>
	
	<div class="form-group">
		<label for="reppassword" class="col-sm-4 control-label">Repeat password:</label>
		<div class="col-sm-8">
			<input type="password" id="reppassword" name="reppassword" class="form-control" placeholder="Repeat password">
		</div>
	</div>
	
	<div class="form-group">
		<label for="uname" class="col-sm-4 control-label">Name:</label>
		<div class="col-sm-8">
			<input type="text" id="uname" name="uname" class="form-control" placeholder="Enter your name">
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-4 col-sm-8">
			<input name="submit" type="submit" class="btn btn-primary" value=" Sign In " />
		</div>
	</div>
</form>