<%-- 
    Document   : login
    Created on : Feb 15, 2017, 9:08:57 PM
    Author     : ghaith husari
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Iosephus.css">
        <title>Eius Inventory System</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="membership?action=loginVerification" name="loginForm" method="POST" class="rightLogin">  
            <label>Username</label><input type="text" name="email"><br>
            <label>Password</label><input type="text" name="password"><br>
            <input type="submit" value="login" name="login" />
	</form>
        <a href="membership?action=signup" name="signup">New User? Click here to register</a>
    </body>
</html>
