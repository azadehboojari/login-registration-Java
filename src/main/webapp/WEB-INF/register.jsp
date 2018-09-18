<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    
<!DOCTYPE html>
<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>home page</title>
	 
	</head>
<body>

<form:form action="/users" method="POST" modelAttribute="user">
	<h1>Registration</h1>
	<c:if test="${userError!= null}">
		<p class="error">${userError}</p>				
	</c:if>
    <p>
        <form:errors path="first"/>
        <form:input path="first" type="text" placeholder="First Name"/>
    </p>
    <p>
        <form:errors path="last"/>
        <form:textarea path="last" type="text" placeholder="Last Name"/>
    </p>
    <p>
        <form:errors path="email"/>
        <form:input type="email" path="email" placeholder="Email"/>
    </p>
    <p>
        <form:errors path="password"/>     
        <form:input type="password" path="password" placeholder="Password"/>
    </p> 
    <p>
   		<input type="password" name="confirm" placeholder="confirm password">
    </p>   
    <input type="submit" value="Register"/>
</form:form>   


	<form action="/users/login" method="POST">
	<h1>Login</h1>
		<c:if test="${loginError != null}" >
			<p class="error">${loginError}</p>				
		</c:if>
		<p><input type="text" name="email" type="text" placeholder="Email"><p>
		<p><input type="text" name="password" type="password" placeholder="password"></p>
		<p><input type="submit" value="login"/></p>
	</form>
</body>
</html> 