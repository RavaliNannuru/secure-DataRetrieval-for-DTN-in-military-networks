

package secure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Connections {
     Connection con;
     Statement stmt;
    public Connections()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/secure", "root", "root");
         
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet Query(String qry)
    {
        try {
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(qry);
            if(rs != null) {
                return rs;
            }
            else {
                return rs;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public  static void main(String aaa[])
    {
        Connections connections = new Connections();
    }
    
}
