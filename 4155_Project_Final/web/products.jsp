<%-- 
    Document   : products
    Created on : Feb 15, 2018, 8:20:09 PM
    Author     : ghaith husari
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Iosephus.css">
        <title>Products</title>
    </head>
    
    <body>
        <p> Welcome back, <c:out value='${user.firstName}'/>. <br/>
            <a href="membership?action=logout">Logout</a> </p>
        
        <h2>Products</h2>
        <table>
            <tr><th> Code </th> <th> Description </th> <th> Price </th> 
                <th></th> <th></th> </tr>
            <!-- The For Each that Loops Through the Products -->
            <c:forEach var="Product" items="${products}">
             <tr>
                <td>${Product.code}</td>
                <td>${Product.description}</td> 
                <td>$ ${Product.price}</td>
                <td> 
                    <form action="productManagement?action=displayProduct" method="post">
                        <input type="hidden" name="productCode" value="<c:out value='${Product.code}'/>"/>
                        <input type="submit" value="Edit" />
                    </form>
                </td> 
                <td> 
                    <form action="productManagement?action=deleteProduct" method="post">
                        <input type="hidden" name="productCode" value="<c:out value='${Product.code}'/>"/>
                        <input type="submit" value="Delete" />
                    </form>
                </td> 
            </tr>
             </c:forEach>
        </table>
        
        <a href="/FinalProject/product.jsp"> <button> Add Product </button> </a>
    </body>
</html>
