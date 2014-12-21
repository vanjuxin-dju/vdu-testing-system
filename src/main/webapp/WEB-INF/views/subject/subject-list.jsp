<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url value="/subject/" var="subjectDeleteUrl"/>

<script type="text/javascript">

function subjectDelete(subjectId) {
	
	$.ajax({
        url: "${subjectDeleteUrl}/" + subjectId,
        type: "DELETE",
        success: function(obj) {
       		var tr = $("#c"+subjectId);
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

<h1>Subjects: </h1>
 
<c:if test="${not empty user}">
	<c:if test="${user.role == 'admin' }">
		<a href="<c:url value="/subject/new" />">New Subject</a> 
	</c:if>
</c:if>


<br/><br/>
<table id="clientsTable" border="1" cellpadding="10" cellspacing="0" class="table table-striped table-bordered">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<c:if test="${not empty user}">
			<c:if test="${user.role == 'admin' }">
				<th>Admin</th><th></th>
			</c:if>
		</c:if>
	</tr>
	
	<c:forEach items="${subjects}" var="subject">
		<c:url value="/subject/${subject.id}" var="viewSubjectUrl"/>
		
		<tr id="c${subject.id}">
		    <td>${subject.id}</td>
		    <td><a href="${viewSubjectUrl}">${subject.name}</a></td> 
		    <td>${subject.admin.name}  &nbsp;  &nbsp;</td>
		    
		    <c:if test="${not empty user}">
				<c:if test="${user.role == 'admin' }">
					<td>
				    	<a href="<c:url value="/subject/edit/${subject.id}" />"> Edit </a> &nbsp;
				   	</td>
				   	<td>
				    	<button type="button" onclick="subjectDelete(${subject.id})">Delete</button> 
				   	</td>
				</c:if>
			</c:if>
		    
		    
	   	</tr>
	</c:forEach>
</table>

<div id="result"></div>