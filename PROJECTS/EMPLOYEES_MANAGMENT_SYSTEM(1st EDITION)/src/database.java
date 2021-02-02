import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class database {
    private static Connection db=null;
    private static database adt=null;
    private static String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
    private database() throws SQLException, ClassNotFoundException
    {
    	Class.forName("com.mysql.jdbc.Driver");
    	db=DriverManager.getConnection(this.dbUrl);
    }
    public static database getInstance() throws ClassNotFoundException, SQLException
    {
    	if(database.db==null)
    	{
    		database.adt=new database();
    	}
    	return database.adt;
    }
    public void insertEmployee(String fn,String did,double salary,String cv) throws SQLException
    {
    	String sql="insert into employee(fullname,did,salary,bio) value(?,?,?,?)";
    	PreparedStatement ps=database.db.prepareStatement(sql);
    	ps.setString(1,fn);
    	ps.setNString(2,did);
    	ps.setDouble(3, salary);
    	ps.setString(4,cv);
    	ps.executeUpdate();
    	ps.close();
    }
    public List <employee> employees() throws SQLException
    {
    	String sql="select * from employee";
    	List <employee> emps=new ArrayList<employee>();
    	Statement st=database.db.createStatement();
    	st.executeQuery(sql);
    	ResultSet rs=st.getResultSet();
    	while(rs.next())
    	{
    		emps.add(new employee(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getString(4)));
    	}
    	rs.close();
    	st.close();
    	return emps;
    }
    public Vector <String> departments() throws SQLException
    {
    	Vector <String> deps=new Vector <String>();
    	String sql="select * from department";
    	Statement st=database.db.createStatement();
    	st.executeQuery(sql);
    	ResultSet rs=st.getResultSet();
    	while(rs.next())
    	{
    	   deps.add(rs.getString(1));	
    	}
    	rs.close();
    	st.close();
    	return deps;
    }
}
