<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activities home</title>
</head>
<body>
<h1>Impiegati</h1>

<div style="display: inline">
	<c:url value="/employees" var="emp"/>
	<table>
	<tr>
    <th>Nome</th>
    <th>Cognome</th>
  	</tr>
  	
	<c:forEach items="${employees}" var="employee" varStatus="status">
	<tr>
		<td>${employee.firstName}</td>
		<td>${employee.lastName}</td>
	</tr>
	<td></td>
	<td colspan="2">
		
	</td>
	</c:forEach>
	
	</table>
</div>

</body>
</html>