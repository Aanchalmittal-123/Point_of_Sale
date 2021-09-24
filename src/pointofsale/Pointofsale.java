
package pointofsale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pointofsale {

    
    Connection conn;
    Statement st;
    public Pointofsale(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  //registering java sql driver
            conn =DriverManager.getConnection("jdbc:mysql://localhost/point_sale","root","");    //preparing connection
            st =conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);  //cretes the statements 
            System.out.println("hello");
            
           
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  

}
    
    

