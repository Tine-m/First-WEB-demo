<%-- 
    Document   : newjsp
    Created on : 06-03-2015, 11:19:31
    Author     : hau
--%>

<%@page import="domain.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Get Order</h1>

        <form action="UIServlet">
            Order No: <br><input type="text" name="OrderNo">
            <input type="hidden" name="command" value="getOrder">
            <button name="getOrderButton" value="">Get Order</Button>
        </form>

       
        ----------------------------------------------------
        <form action="index.html">
            <button name="MenuButton" value="">Menu</button>
        </form>
    </body>
</html>
