<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   

<h1>New Test: </h1>

<form action="${action}" method="post">
	<input type="text" name="question" placeholder="Enter Question" /><br />
	<input type="text" name="option[]" placeholder="Option 1" /> <label><input type="checkbox" name="correct[]" value="0"> Correct</label> <br />
	<input type="text" name="option[]" placeholder="Option 2" /> <label><input type="checkbox" name="correct[]" value="1"> Correct</label> <br />
	<div id="add"><input type="button" onclick="addOption()" value="Add option"></div>
	<input type="submit" value="Save" />
	<input type="reset" value="Cancel" onclick="window.history.back();"/>
</form>
<script type="text/javascript">
	var opt = 3;
	function addOption() {
		$("<input type='text' name='option[]' placeholder='Option " + opt 
				+ "' /> <label><input type='checkbox' name='correct[]' value='" + (opt - 1) + "'> Correct</label> <br />")
				.insertBefore("#add");
		opt++;
	}
</script>
