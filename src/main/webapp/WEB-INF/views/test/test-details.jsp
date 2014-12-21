<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/question/" var="questionUrl" />

<h1>Test Details: ${test.name}</h1>

ID: ${test.id} <br/>
Name: ${test.name} <br/>

<br/>
<br/>

<h3>Questions</h3>
<div id="questions"></div>
<c:if test="${not empty user}">
	<c:if test="${user.role == 'admin' }">
		<div><a href="<c:url value="/question/test/${test.id}/new" />">Add question</a></div>
		<script type="text/javascript">
			function questionDelete(questionId) {
				$.ajax({
			        url: "${questionUrl}/" + questionId,
			        type: "DELETE",
			        success: function(obj) {
			       		var tr = $("#c"+questionId);
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
			
			$(document).ready(function() {
				$.get("${questionUrl}/list/${test.id}", function(data) {
					//console.log(data);
					var table = $("<table />").appendTo("#questions");
					$(data).each(function(i, item) {
						var options = $("<table />");
						$(item.options).each(function(j, item2) {
							$("<tr />")
								.append($("<td />").html("<label><input type='checkbox' class='options' id='op" + item2.id + "' " 
										+ (item2.correct ? "checked" : "") + "> " + item2.optionContent + "</label>"))
								.appendTo(options);
							$("#op"+item2.id).prop("checked", item2.correct);
						});
						$("<tr />")
							.append($("<td />").html(item.questionContainer + "<br/>").append(options))
							.append($("<td />").html("<a href='${questionUrl}/test/${test.id}/edit/" + item.id + "'>Edit</a>"))
							.append($("<td />").html("<button type='button' onclick='questionDelete(" + item.id + ")'>Delete</button>"))
							.attr("id", "c" + item.id)
							.appendTo(table);
					});
				});
			});
		</script>
	</c:if>
	<c:if test="${user.role == 'user' }">
		<script type="text/javascript">
			var options = []
			localStorage.clear();
			function send() {
				for (var i = 0; i < localStorage.length; i++) {
					options.push(localStorage.key(i));
				}
				$.post("${questionUrl}/test/${test.id}/answers", 
						{ answers: options })
					.done(function(obj) {
						alert("success!");
						localStorage.clear();
						$("#send").attr("disabled","");
					})
					.fail(function() {
						alert("failure!");
					});
			}
			
			function toggleSelect(oId) {
				if (localStorage.getItem(oId))
					localStorage.removeItem(oId);
				else
					localStorage.setItem(oId, true);
			}
			
			$(document).ready(function() {
				$.get("${questionUrl}/list/${test.id}", function(data) {
					//console.log(data);
					var table = $("<table />").appendTo("#questions");
					$(data).each(function(i, item) {
						var options = $("<table />");
						$(item.options).each(function(j, item2) {
							$("<tr />")
								.append($("<td />").html("<label><input type='checkbox' onclick='toggleSelect(" + item2.id + ")' class='options' " 
										+ (localStorage.getItem(item2.id) ? "checked" : "") + "> " + item2.optionContent + "</label>"))
								.appendTo(options);
						});
						$("<tr />")
							.append($("<td />").html(item.questionContainer + "<br/>").append(options))
							.attr("id", "c" + item.id)
							.appendTo(table);
					});
				});
			});
		</script>
		<input type="button" id="send" class="btn btn-primary" onclick="send()" value="Send">
	</c:if>
</c:if>
