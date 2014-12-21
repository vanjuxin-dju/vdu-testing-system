<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/test/" var="testUrl"/>

<h1>Subject Details: ${subject.name}</h1>

ID: ${subject.id} <br/>
Name: ${subject.name} <br/>
Admin: ${subject.admin.name} <br/>

<br/>
<br/>
<h3>Tests</h3>
<div id="tests"></div>
<c:if test="${not empty user}">
	<c:if test="${user.role == 'admin' }">
		<div><a href="<c:url value="/test/subject/${subject.id}/new" />">Add new</a></div>
		<script type="text/javascript">
			$(document).ready(function() {
				$.get("${testUrl}/list/${subject.id}", function(data) {
					//console.log(data);
					var table = $("<table />").appendTo("#tests");
					$(data).each(function(i, item) {
						$("<tr />")
							.append($("<td />").html("<a href='${testUrl}/" + item.id + "'>" + item.name + "</a>"))
							.append($("<td />").html("<a href='${testUrl}/subject/${subject.id}/edit/" + item.id + "'>Edit</a>"))
							.append($("<td />").html("<button type='button' onclick='testDelete(" + item.id + ")'>Delete</button>"))
							.attr("id", "c" + item.id)
							.appendTo(table);
					});
				})
			});
			
			function testDelete(testId) {
				
				$.ajax({
			        url: "${testUrl}/" + testId,
			        type: "DELETE",
			        success: function(obj) {
			       		var tr = $("#c"+testId);
			            tr.css("background-color","#FF3700");
			            tr.fadeOut(400, function(){
			                tr.remove();
			            });
			            
			            console.log("deleted");
			        },
			        error: function(){
			            alert('failure');
			        }
			    });
				return false;
			}
		</script>
	</c:if>
	<c:if test="${user.role == 'user' }">
		<script type="text/javascript">
			$(document).ready(function() {
				$.get("${testUrl}/list/${subject.id}", function(data) {
					var table = $("<table />").appendTo("#tests");
					$(data).each(function(i, item) {
						$("<tr />")
							.append($("<td />").html("<a href='${testUrl}/" + item.id + "'>" + item.name + "</a>"))
							.attr("id", "c" + item.id)
							.appendTo(table);
					});
				})
			});
		</script>
	</c:if>
</c:if>


