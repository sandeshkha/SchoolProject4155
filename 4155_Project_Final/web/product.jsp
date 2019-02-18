<%-- 
    Document   : product
    Created on : Feb 16, 2017, 9:53:43 AM
    Author     : Kassandra Walker & David McAlister
--%>

<%@ include file="header.jsp" %>
    </head>
    <body>
        <p> Welcome back, <c:out value='${user.firstName}'/>. <br/>
            <a href="membership?action=logout">Logout</a> </p>
        
        <h2>Product</h2>
        <c:out value="${error}" />
        <form action="productManagement?action=displayProducts" method="POST">
            <label> Code: </label>
            <input type="text" name="code" value="${product.code}"/> <br />
            <label> Description: </label>
            <textarea name="description" height="50px;">${product.description}</textarea> <br />
            <label> Price: </label>
            <input type="text" name="price" value="${product.price}"/> <br />
            
            <input type="hidden" name="update" value="yes"/>
            <input type="submit" value="Add or Update Product" />
        </form>
            <br />
            <a href="productManagement?action=displayProducts"> <button class="left"> View All Products </button></a>
    </body>
</html>
