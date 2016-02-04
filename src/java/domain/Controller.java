package domain;

import java.util.ArrayList;
import dataSource.*;
//=== hau

public class Controller
{

    private Order currentOrder;       // Order in focus
    private DBFacade dbf;

    //-- singleton
    private static Controller instance;

    private Controller()
    {
        currentOrder = null;
        dbf = DBFacade.getInstance();
    }

    public static Controller getInstance()
    {
        if (instance == null)
        {
            instance = new Controller();
        }
        return instance;
    }
    //----------------------

    public Order getOrder(int ono)
    {
        currentOrder = dbf.getOrder(ono);
        return currentOrder;
    }

    public Order getCurrentOrder()
    {
        return currentOrder;
    }

    public Order createNewOrder(int cno, int eno)
    {
        //== create order object with ono=0
        currentOrder = new Order(0, cno, eno, null, null);

        //== save and get DB-generated unique ono
        boolean status = dbf.saveNewOrder(currentOrder);
        if (!status) //fail!
        {
            currentOrder = null;
        }

        return currentOrder;
    }

    public boolean addOrderDetail(int partNo, int qty)
    {
        boolean status = false;
        if (currentOrder != null)
        {
            OrderDetail od = new OrderDetail(currentOrder.getOno(), partNo, qty);
            status = dbf.saveNewOrderDetail(od);
            if (status)
            {
                currentOrder.addDetail(od);
            }
        }
        return status;
    }

    public String getOrderDetailsToString()
    {
        if (currentOrder != null)
        {
            return currentOrder.detailsToString();
        } else
        {
            return null;
        }
    }

    //====  Exercise   
    public Order updateOrder(int cno, int eno, String rec, String ship)
    {
        boolean status = false;
        if (currentOrder != null)
        {
            currentOrder.setCustomer(cno);
            currentOrder.setEmployee(eno);
            status = dbf.updateOrder(currentOrder);
        }
        if (!status)  //fail
        {
            currentOrder = null;
        }

        return currentOrder;
    }

    //====  Exercise 
    public boolean updateOrderDetail(int partNo, int quantity)
    {
        boolean status = false;
        OrderDetail od = null;
        if (currentOrder != null)
        {
            od = currentOrder.updateDetail(partNo, quantity);
        }
        if (od != null)
        {
            status = dbf.updateOrderDetail(od);
        }
        if (!status)  // fail
        {
            currentOrder = null;
        }

        return status;
    }

}
