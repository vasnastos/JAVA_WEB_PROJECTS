package products_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class database {
    private static database db;
    private static Connection con;
    private String dbURL="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
    private database() throws ClassNotFoundException, SQLException
    {
    	Class.forName("com.mysql.jdbc.Driver");
    	database.con=DriverManager.getConnection(dbURL);
    }
    public static database getInstance() throws ClassNotFoundException, SQLException
    {
    	if(database.db==null)
    	{
    		database.db=new database();
    	}
    	return database.db;
    }
    public Vector <product> employees() throws SQLException
    {
    	String sql="select * from product";
       Vector <product> emps=new Vector<product>();
       Statement st=database.con.createStatement();
       ResultSet rs=st.executeQuery(sql);
       while(rs.next())
       {
    	   emps.add(new product(rs.getString(1),rs.getString(2),rs.getDouble(3)));
       }
       rs.close();
       st.close();
       return emps;
    }
}
