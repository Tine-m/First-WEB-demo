package dataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Order;
import domain.OrderDetail;

//=== Maps between classes and tables
//=== Encapsulates SQL-statements

public class OrderMapper
{
  //== load an order and the associated order details
  public Order getOrder(int ono, Connection con) 
  {
      Order o = null;
      String SQLString1 =       // get order
          "select * " +
          "from orders " +
          "where ono = ?"; 
      String SQLString2 =         // get order details
        "select od.pno, od.qty " +
        "from odetails od " +
        "where od.ono = ? ";         // foreign key match 
      PreparedStatement statement=null;
      
      try
      {
          //=== get order
          statement = con.prepareStatement(SQLString1);
          
          statement.setInt(1,ono);     // primary key value
          ResultSet rs  = statement.executeQuery();
          if (rs.next())
          {
            o = new Order(ono, 
                    rs.getInt(2), 
                    rs.getInt(3), 
                    rs.getDate(4),
                    rs.getDate(5));
          }         
          
        //=== get order details
        statement = con.prepareStatement(SQLString2);
        statement.setInt(1,ono);          // foreign key value
        rs = statement.executeQuery();
        while (rs.next())
        {
          o.addDetail(new OrderDetail(
                        ono,
                        rs.getInt(1),
                        rs.getInt(2)));
        }          
      }
      catch (Exception e)
      {   
          System.out.println("Fail1 in OrderMapper - getOrder");
          System.out.println(e.getMessage());
      }
      finally														// must close statement
      {
    	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail2 in OrderMapper - getOrder");
			System.out.println(e.getMessage());
		}  
      }
      return o;                         
  } 
  
  //== Insert new order (tuple)
  public boolean saveNewOrder(Order o, Connection con)
  { 
    int rowsInserted = 0;
    String SQLString1 =  "SELECT max(Auto_increment) FROM information_schema.tables WHERE table_name='orders';";
        /*"select orderseq.nextval  " +
        "from dual" ;*/
    String SQLString2 = "insert into orders values (?,?,?,?,?)";
    PreparedStatement statement=null;
    
    try{
        //== get unique ono
        statement = con.prepareStatement(SQLString1);
        ResultSet rs  = statement.executeQuery();
        if (rs.next())
        {
          o.setOno(rs.getInt(1));
        }
        
        //== insert tuple
        statement = con.prepareStatement(SQLString2);
        statement.setInt    (1, o.getOno());
        statement.setInt    (2, o.getCustomerNo());
        statement.setInt    (3, o.getEmployeeNo());
        statement.setDate   (4, o.getReceived());
        statement.setDate   (5, o.getShipped());      
        rowsInserted  = statement.executeUpdate();
    }
    catch (Exception e)
    {   
        System.out.println("Fail1 in OrderMapper - saveNewOrder");
        System.out.println(e.getMessage());
    }
    finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail2 in OrderMapper - saveNewOrder");
			System.out.println(e.getMessage());
		}  
    }    
    return rowsInserted == 1;
  }

  //== Insert new order detail (tuple)
  public boolean saveNewOrderDetail(OrderDetail od, Connection con)
  {
    int rowsInserted =0;
    String SQLString = 
        "insert into odetails " +
        "values (?,?,?)" ;  
    PreparedStatement statement=null;
    
    try{        
        //== insert tuple
        statement = con.prepareStatement(SQLString);
        statement.setInt    (1, od.getOno());
        statement.setInt    (2, od.getPno());
        statement.setInt    (3, od.getQty());
        rowsInserted  = statement.executeUpdate();
    }
    catch (Exception e)
    {   
        System.out.println("Fail1 in OrderMapper - saveNewOrderDetail");
        System.out.println(e.getMessage());
    }
    finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail2 in OrderMapper - saveNewOrderDetail");
			System.out.println(e.getMessage());
		}  
    }
    return rowsInserted == 1;
  }

    // Exercise
    //== update order tuple
    boolean updateOrder(Order o, Connection con) {
       
        int rowsUpdated  = 0;
        String SQLString = 
            "update orders set cno = ?, eno = ? where ono = ?";
        PreparedStatement statement=null;
    
    try{
        //== update order tuple
        statement = con.prepareStatement(SQLString);
        statement.setInt    (1, o.getCustomerNo());
        statement.setInt    (2, o.getEmployeeNo());
        statement.setInt    (3, o.getOno());
    
        rowsUpdated  = statement.executeUpdate();
    }
    catch (Exception e)
    {   
        System.out.println("Fail1 in OrderMapper - updateOrder");
        System.out.println(e.getMessage());
    }
    finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail2 in OrderMapper - updateOrder");
			System.out.println(e.getMessage());
		}  
    }    
    return rowsUpdated == 1;
  }
    
    
    // Exercise
    boolean updateOrderDetail(OrderDetail od, Connection con) {      
        int rowsUpdated   = 0;
        String SQLString  = 
            "update odetails set qty = ? where pno = ? and ono = ?";
        PreparedStatement statement = null;
    
    try{
        //== update order tuple
        
        statement = con.prepareStatement(SQLString);
        statement.setInt    (1, od.getQty());
        statement.setInt    (2, od.getPno());
        statement.setInt    (3, od.getOno());
        rowsUpdated  = statement.executeUpdate();
        
    }
    catch (Exception e)
    {   
        System.out.println("Fail1 in OrderMapper - updateOrderDetail");
        System.out.println(e.getMessage());
    }
    finally														// must close statement
    {
  	  try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Fail2 in OrderMapper - updateOrderDetail");
			System.out.println(e.getMessage());
		}  
    }    
    return rowsUpdated == 1;
    }

}
