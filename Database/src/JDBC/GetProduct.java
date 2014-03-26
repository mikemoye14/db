package JDBC;
import java.util.*;
import java.io.*;
import java.sql.*;

/**
 *
 * @author MTM5313
 */
public class GetProduct 
{
    
    private static Scanner sc = new Scanner(System.in);    
    
    public static void main (String args[]) 
    {
        
        System.out.print("Enter the product ID.\n");
        
        String id = sc.next();
        
        getDatabase(id);
    }
    
    private static void getDatabase(String productID){
        
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
            //System.out.println(url);
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
        }
        
        try
        {
             Connection con = DriverManager.getConnection(url,user,password);     
                
            Statement stmt = con.createStatement();
            
            //System.out.print("SELECT ProductName from Inventory WHERE productID = '" + productID + "'");
            
            ResultSet rs = stmt.executeQuery("SELECT ProductName from Inventory WHERE productID = '" + productID + "'");

            while (rs.next())
            {
                String product = rs.getString("ProductName");

                System.out.println("The product name is: '" + product + "'.");
            }
            stmt.close();
        
            con.close();
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
        
    }    
    
}

