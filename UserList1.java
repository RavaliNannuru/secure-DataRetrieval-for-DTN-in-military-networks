/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package secure;

import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;


public class UserList1 {

    
     public UserList1()
    {
        Vector columnNames = new Vector();
        Vector data = new Vector();
        JPanel p=new JPanel();
        Connection con;
        int columns ;
		try 
		{            
            con=new Connections().con;
            String sql = "select name,location,upload,download,exptim from user";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            columns = md.getColumnCount();
            
            
            for (int i = 1; i <= columns; i++) 
			{
            columnNames.addElement( md.getColumnName(i) );
            }
            while (rs.next()) {
            Vector row = new Vector(columns);
            for (int i = 1; i <= columns; i++)
			{
			String val="";
			val=rs.getString(i);
			if(i==1||i==2||i==5)
			row.addElement(val);
			else
			{
			if(val.trim().equals("no"))
            row.addElement("No");
			else
			row.addElement("Yes");
			}
            }
            data.addElement(row);
        }
        rs.close();
        stmt.close();
}
                catch(Exception e){
                    System.out.println(e);
                    }
                JTable table = new JTable(data, columnNames);
                table.setBackground(Color.white);
                table.setGridColor(Color.gray);
                table.setForeground(Color.blue);
                table.setCellSelectionEnabled(false);
                TableColumn col;
                for (int i = 0; i < table.getColumnCount(); i++) 
                {
                col = table.getColumnModel().getColumn(i);  
                col.setMaxWidth(250);
                   }
                JScrollPane scrollPane = new JScrollPane( table );
                p.add( scrollPane );
                JFrame f=new JFrame();
                f.add(p);
                f.setSize(600,400);
                f.setVisible(true);
                f.setTitle("Attribute Revocation");
}
}
