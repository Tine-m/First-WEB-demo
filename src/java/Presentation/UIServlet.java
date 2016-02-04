package Presentation;

import domain.Controller;
import domain.Order;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Henrik
 */
@WebServlet(name = "UIServlet", urlPatterns =
{
    "/UIServlet"
})
public class UIServlet extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        {
            //-- Establish or reestablish application context
            HttpSession sessionObj = request.getSession();
            Controller con = (Controller) sessionObj.getAttribute("Controller");
            if (con == null)
            {
                // Session starts
                con = Controller.getInstance();
                sessionObj.setAttribute("Controller", con);
            } else
            {
                con = (Controller) sessionObj.getAttribute("Controller");
            }

            //-- Identify command and delegate job
            String command = request.getParameter("command");
            switch (command)
            {
                case "getOrder":
                    getOrder(request, response, con);
                    break;
                case "newOrder":
                    createOrder(request, response, con);
                    break;
                case "newOrderDetail":
                    createNewOrderDetail(request, response, con);
                    break;
                    
                //== exercise
                case "updateOrder":
                    updateOrder(request, response, con);
                    break;
                //== exercise
                case "updateOrderDetail":
                    updateOrderDetail(request, response, con);
                    break;
            }
        }
    }

    private void getOrder(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException
    {
        // get input
        int ono = Integer.parseInt(request.getParameter("OrderNo"));

        // do work and get data to output
        Order order = con.getOrder(ono);
        String[][] od = null;
        if (order != null)
        {
            od = order.details();
        }

        // show output
        request.setAttribute("order", order);
        request.setAttribute("orderdetail", od);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
        dispatcher.forward(request, response);
    }

    private void createOrder(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException
    {
        // get input
        int cno = Integer.parseInt(request.getParameter("CustomerNo"));
        int eno = Integer.parseInt(request.getParameter("EmployeeNo"));

        // do work and get data to output
        Order order = con.createNewOrder(cno, eno);
        String[][] od = null;
        if (order != null)
        {
            od = order.details();
        }

        // show output
        request.setAttribute("order", order);
        request.setAttribute("orderdetail", od);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
        dispatcher.forward(request, response);

    }

    private void createNewOrderDetail(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException
    {
        // get input
        int pno = Integer.parseInt(request.getParameter("PartsNo"));
        int qty = Integer.parseInt(request.getParameter("Quantity"));

        // do work and get data to output
        boolean status = con.addOrderDetail(pno, qty);
        Order order = con.getCurrentOrder();
        String[][] od = null;
        if (order != null)
        {
            od = order.details();
        }

        // show output
        request.setAttribute("status", status);
        request.setAttribute("order", order);
        request.setAttribute("orderdetail", od);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
        dispatcher.forward(request, response);

    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException
    {
        // get input
        int eno = Integer.parseInt(request.getParameter("EmployeeNo"));
        int cno = Integer.parseInt(request.getParameter("CustomerNo"));

        // do work and get data to output

        
        Order order = con.updateOrder(cno, eno, null, null);
        String[][] od = null;
        if (order != null)
        {
            od = order.details();
        }

        // show output        
        request.setAttribute("order", order);
        request.setAttribute("orderdetail", od);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
        dispatcher.forward(request, response);
    }
    private void updateOrderDetail(HttpServletRequest request, HttpServletResponse response, Controller con) throws ServletException, IOException
    {
        // get input
        int pno = Integer.parseInt(request.getParameter("ProductNo"));
        int qty = Integer.parseInt(request.getParameter("Quantity"));

        // do work and get data to output
        boolean status = con.updateOrderDetail(pno, qty);
        Order order = con.getCurrentOrder();
        String[][] od = null;
        if (order != null)
        {
            od = order.details();
        }

        // show output
        request.setAttribute("status", status);
        request.setAttribute("order", order);
        request.setAttribute("orderdetail", od);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOrder.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
