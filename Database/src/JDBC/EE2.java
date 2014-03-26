package JDBC;
import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author MTM5313
 */
public class EE2  
{
    public static void main (String args[]) 
    {
        String className=null;
        String url=null;
        String user = null;
        String password = null;
        
        try
        {
            ResourceBundle resources;
            InputStream in = null;
            ResourceBundle newResources;

            in = ClassLoader.getSystemResourceAsStream("db.properties");

            resources = new PropertyResourceBundle(in);

            in.close();

            className = resources.getString("jdbc.driver");
            url = resources.getString("jdbc.url");
            System.out.println(url);
            user = resources.getString("jdbc.user");
            password = resources.getString("jdbc.password");
        }
        catch (Exception exp)
        {
            System.out.println("Couldn't load resources." + exp);
            System.exit(-1);
        }
        
        try
        {
            Class.forName(className);
        }
        catch (Exception e) 
        {
            System.out.println("Failed to load  driver.");
            return;
        }
        
        try
        {
             Connection con = DriverManager.getConnection(url,user,password);     
                
            Statement stmt = con.createStatement();
            
            stmt.execute("CREATE TABLE SalesHistory4" +
                         "(ProductID varchar(50)," +
                         "Price int," +
                         "TrnsDate varchar(255)," +
                         "primary key (price)," + 
                         "foreign key (ProductID) references Inventory(ProductID))");
        
            System.out.println("Created Sales History table");
        
            stmt.close();
        
            con.close();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }
}

