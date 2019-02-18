<%-- 
    Document   : signup
    Created on : Feb 15, 2018, 9:24:41 PM
    Author     : ghaith husari
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Iosephus.css">
        <title>SQL sign-up</title>
    </head>
    <body>
        <h1>Sign-up form</h1>
        <form action="membership?action=signup" name="loginForm" method="POST" class="rightLogin">  
            <label>First Name</label><input type="text" name="firstname"><br>
            <label>Last Name</label><input type="text" name="lastname"><br>
            <label>Email</label><input type="text" name="email"><br>
            <label>Password</label><input type="password" name="password"><br>
            <input type="submit" value="Sign up" name="signup" />
	</form>
    </body>
</html>
