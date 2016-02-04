package dataSource;

import java.sql.Connection;
import domain.*;

// Encapsulates the Data Source Layer
// Encapsulates connection handling 
// Implemented as a Singleton to provide global access from
// Domain classes and to ensure the use of max one connection
public class DBFacade {

	  private OrderMapper om; 
	  private Connection con;
	  
	  //== Singleton start
	  private static DBFacade instance;
	 
	  private DBFacade() {
		  om 	= new OrderMapper();
		  con 	= DBConnector.getInstance().getConnection();  		  
	  }
	  public static DBFacade getInstance()
	  {
		  if(instance == null)
			  instance = new DBFacade();
		  return instance;
	  }
	  //== Singleton end

	  
	  public Order getOrder(int ono) 
	  {
		  return om.getOrder(ono, con);	      
	  }
	  
	  public boolean saveNewOrder(Order o)
	  { 
	    return om.saveNewOrder(o, con);
	  }
	  
	  public boolean saveNewOrderDetail(OrderDetail od)
	  {
	    return om.saveNewOrderDetail(od, con);
	  }

            public boolean updateOrder(Order o) {
                return om.updateOrder(o, con);
            }

        public boolean updateOrderDetail(OrderDetail od) {
            return om.updateOrderDetail(od, con);
        }
	
}
