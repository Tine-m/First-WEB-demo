<%-- 
    Document   : NewOrder
    Created on : 06-03-2015, 11:35:06
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
        <h1>New Order</h1>
        
        <form action="UIServlet">           
            Employee No: <br><input type="text" name="EmployeeNo"><br>
            Customer No: <br><input type="text" name="CustomerNo">
            <input type="hidden" name="command" value="newOrder">
            <button name="newOrderButton" value="" >New Order</Button>
        </form>

        <br>
        <% Order newo = (Order) request.getAttribute("neworder");%>
        <% if (newo != null)
            {%>
        <h4>New Order</h4>
        Order    No: <%= newo.getOno()%><br>
        Customer No: <%= newo.getCustomerNo()%><br>
        Employee No: <%= newo.getEmployeeNo()%><br>
        <% }  %>
        
        <br>
        -------------------------------------------------------------------
        <form action="index.html">
            <button name="MenuButton" value="">Menu</button>
        </form>
    </body>
</html>
