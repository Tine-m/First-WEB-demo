<%-- 
    Document   : ShowOrder
    Created on : 06-03-2015, 11:38:02
    Author     : hau
--%>

<%@page import="domain.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show Order</title>
    </head>
    <body>
        <h1>Show Order</h1>

        <% Order o = (Order) request.getAttribute("order");%>
        <%
            if (o != null)
            {%>
        <h2>Order</h2>
        Order    No: <%= o.getOno()%>
        <br>
        <form action="UIServlet">
            Employee No:<%= o.getEmployeeNo()%><input type="text" name="EmployeeNo" value="" >
            <input type="hidden" name="CustomerNo" value="<%= o.getCustomerNo()%>">
            <input type="hidden" name="command" value="updateOrder">
            <button name="MenuButton" value="">Update</button>
        </form>
        <form action="UIServlet">            
            Customer No:<%= o.getCustomerNo()%><input type="text" name="CustomerNo" value= "">
            <input type="hidden" name="EmployeeNo" value="<%= o.getEmployeeNo()%>">
            <input type="hidden" name="command" value="updateOrder">
            <button name="MenuButton" value="">Update</button>
        </form>
        <br>
        ------------------------------------------------------------------------
        <h2>Order Details</h2>

        <%
            String[][] orderDetails = (String[][]) request.getAttribute("orderdetail");%>
        <table>
            <tr>
                <th>Order No</th>
                <th>Parts No</th>
                <th>Quantity</th>
                <th>New Quantity</th>
            </tr>
            <%
                for (int i = 0; i < orderDetails.length; i++)
                {
            %>

            <!--               <%= orderDetails[i]%>-->


            <tr>
                <td><%=orderDetails[i][0]%></td>
                <td><%=orderDetails[i][1]%></td>
                <td><%=orderDetails[i][2]%></td>
                <td><form action="UIServlet">
                        <br>
                        <input type="hidden" name="ProductNo" value="<%=orderDetails[i][1]%>" > 
                        <input type="text"   name="Quantity" value="" >                    
                        <input type="hidden" name="command" value="updateOrderDetail">
                        <button name="updateOrderDetailButton" value="" >Update</Button>
                    </form>
                    <!--                  <%=orderDetails[i][2]%> -->
                </td>
            </tr>
            <%}%>
        </table>

        <%}%>

        <br>
        ------------------------------------------------------------------------
        <h2>New Order Detail</h2>
        <form action="UIServlet">
            <table>
                <tr>
                    <th>Parts No</th>
                    <th>Quantity</th>
                    <th></th>
                </tr> 
                <tr>  
                    <td><input type="hidden" name="command" value="newOrderDetail">
                        <input type="text" name="PartsNo"></td>
                    <td><input type="text" name="Quantity"></td>                         
                    <td><button name="newOrderdetailButton" value="" >New</Button></td>                    
                </tr>
            </table>
        </form>


        <!-- Status feedback -->
        <% Boolean statusOK = (Boolean) request.getAttribute("status");%>
        <% if (statusOK != null)
            {
                if (statusOK.booleanValue() == true)
                { %>
        <h4>Inserted/updated order detail</h4><%
        } else
        {%>
        <h4>## Failed to insert/update order detail</h4><%
                }
            }%>
        <br>
        ------------------------------------------------------------------------
        <form action="index.html">
            <button name="MenuButton" value="">Menu</button>
        </form> 
    </body>
</html>
