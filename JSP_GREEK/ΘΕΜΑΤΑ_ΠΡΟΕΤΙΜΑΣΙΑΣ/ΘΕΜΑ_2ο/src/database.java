
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    private static Connection con=null;
    private static database db=null;
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
    public String insert(String name,String id,double pr) throws SQLException
    {
    	String sql="insert into product(pid,pname,price) values(?,?,?)";
    	PreparedStatement st=database.con.prepareStatement(sql);
    	st.setString(1, id);
    	st.setString(2,name);
    	st.setDouble(3,pr);
    	st.executeUpdate();
    	return "New Insert:"+id+"--"+name+"--"+String.valueOf(pr);
    }
    public void open() throws ClassNotFoundException, SQLException
    {
    	database.db=new database();
    }
    public void close() throws SQLException
    {
    	database.con.close();
    	database.db=null;
    }
}
