package dataSource;

import java.sql.Connection;
import java.sql.DriverManager;

// Encapsulates code to connect to DB
// Implemented as a Singleton to ensure max one connection
public class DBConnector
{

    private static String driver = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost:3306/test";
    private static String id = "root";			
    private static String pw = "root";
    
    private Connection con;

    //-- Singleton ---- 
    private static DBConnector instance;
    private DBConnector()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(URL, id, pw);   // The connection will be released upon program 
		  					      // termination by the garbage collector	
        } catch (Exception e)
        {
            System.out.println("\n*** Remember to insert your Oracle ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }
    }
    public static DBConnector getInstance()
    {
        if (instance == null)
            instance = new DBConnector();
        return instance;
    }
    //------------------
    
    public Connection getConnection()
    {
      return con;
    }
}
