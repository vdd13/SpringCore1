<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form method="post" modelAttribute="osoba" action="testForm">
	<table>
		<tr>
			<td> Imie: </td>
			<td> <form:input path="imie"/> </td> 
		</tr>
		<tr>
			<td colspan="2"> <input type="submit" value="sAve data"/></td>
		</tr>
	</table>

</form:form>
</body>
</html>