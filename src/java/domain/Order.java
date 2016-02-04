package domain;

import java.sql.Date;
import java.util.ArrayList;
//=== hau

public class Order
{

    private int ono;
    private int cno; // A FK that should be mapped to a reference
    private int eno; // A FK that should be mapped to a reference
    private Date received;
    private Date shipped;
    private ArrayList<OrderDetail> orderDetails;

    public Order(int o, int c, int e, Date r, Date s)
    {
        ono = o;
        cno = c;
        eno = e;
        received = r;
        shipped = s;
        orderDetails = new ArrayList<OrderDetail>();
    }

    //== accessors
    public int getOno()
    {
        return ono;
    }

    public void setOno(int ono)
    {
        this.ono = ono;
    }

    public void setCustomer(int c)
    {
        this.cno = c;
    }

    public int getCustomerNo()
    {
        return cno;
    }

    public void setEmployee(int e)
    {
        this.eno = e;
    }

    public int getEmployeeNo()
    {
        return eno;
    }

    public void setReceived(Date received)
    {
        this.received = received;
    }

    public Date getReceived()
    {
        return received;
    }

    public void setShipped(Date shipped)
    {
        this.shipped = shipped;
    }

    public Date getShipped()
    {
        return shipped;
    }

    public void addDetail(OrderDetail od)
    {
        orderDetails.add(od);
    }

    String detailsToString()
    {
        String res = "";
        for (int i = 0; i < orderDetails.size(); i++)
        {
            res += orderDetails.get(i).toString() + "\n";
        }
        return res;
    }

    public String[][] details()
    {
        String[][] res = new String[orderDetails.size()][3];
        for (int i = 0; i < orderDetails.size(); i++)
        {
            res[i][0] = orderDetails.get(i).getOno() + "";
            res[i][1] = orderDetails.get(i).getPno() + "";
            res[i][2] = orderDetails.get(i).getQty() + "";

        }
        return res;
    }

    // Exercise
    OrderDetail updateDetail(int partNo, int quantity)
    {
        OrderDetail od = null;
        boolean found = false;
        int index = 0;
        while (!found && index < orderDetails.size())
        {
            od = orderDetails.get(index);
            if (partNo == od.getPno())
            {
                found = true;
                od.setQty(quantity);
            } else
            {
                index++;
            }
        }
        return od;
    }

    public String toString()
    {
        return ono + " " + cno + " " + eno + " " + received + " " + shipped;
    }
}
