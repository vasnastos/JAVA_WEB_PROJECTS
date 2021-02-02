package javasource;
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
    private String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
    private static database instance=null;
    private static Connection db;
    private database()
    {
    	try {
    		try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("Error in driver");
			}
			db=DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			System.out.println("Connection Fail please check the url!!!!");
		}
    }
    public static database getInstance()
    {
    	if(database.instance==null)
    	{
    		database.instance=new database();
    	}
    	return database.instance;
    }
    public void insert(String n,String ln,String i,String m,int s)
    {
    	String sql="insert into student(name,lastname,id,email,semester) values(?,?,?,?,?)";
    	try {
    		System.out.println(ln);
			PreparedStatement ps=db.prepareStatement(sql);
			ps.setString(1,n);
			ps.setString(2,ln);
			ps.setString(3,i);
			ps.setString(4,m);
			ps.setInt(5,s);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public Vector <student> extract()
    {
    	Vector <student> students=new Vector<student>();
    	try {
    		String sql="select * from student";
    		PreparedStatement ps=database.db.prepareStatement(sql);
			ResultSet rs=ps.executeQuery(sql);
			String name,ln,i,ml;
			int sem;
			while(rs.next())
			{
				i=rs.getString("id");
				name=rs.getString("name");
				ln=rs.getString("lastname");
				sem=rs.getInt("semester");
				ml=rs.getString("email");
				student s=new student(name,ln,i,ml,sem);
				students.add(s);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return students;
    }
    public String update_record(String id,int semester,String name,String ln,String mail)
    {
    	int rows=0;
    	String sql="update student set name=? where id=?";
    	try {
			PreparedStatement ps=this.db.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update student set lastname=? where id=?";
			ps=database.db.prepareStatement(sql);
			ps.setString(1, ln);
			ps.setString(2,id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update student set email=? where id=?";
			ps=database.db.prepareStatement(sql);
			ps.setString(1,mail);
			ps.setString(2, id);
			rows+=ps.executeUpdate();
			ps.close();
			sql="update student set semester=? where id=?";
			ps=database.db.prepareStatement(sql);
			ps.setInt(1,semester);
			ps.setString(2,id);
			rows+=ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	String message="Student "+id+":"+name+"-"+ln+"-"+mail+"-"+String.valueOf(semester);
    	return message;
    }
    
    
    public String delete(String id)
    {
    	String sql="delete from student where id=?";
    	String msg="";
    	int roweffected=0;
    	try {
			PreparedStatement ps=this.db.prepareStatement(sql);
			ps.setString(1,id);
			roweffected=ps.executeUpdate();
			msg="student "+id+" deleted from system";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(roweffected==0) {return "";}
    	return msg;
    }
    public student search(String id)
    {
    	String sql="select * from student where id=?";
    	System.out.println("ID:"+id);
    	student searchstudent=null;
    	try {
			PreparedStatement ps=database.db.prepareStatement(sql);
			ps.setString(1,id);
			ps.execute();
			ResultSet rs=ps.getResultSet();
			if(rs.next())
			{
				String name=rs.getString("name");
				String ln=rs.getString("lastname");
				String mail=rs.getString("email");
				int semester=rs.getInt("semester");
				searchstudent=new student(name,ln,id,mail,semester);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return searchstudent;
    }
    public student last_inserted_value()
    {
    	student s=null;
    	String name,ln,id,mail;
    	int sem;
    	Statement st;
		try {
			st = database.db.createStatement();
			String sql="select * from student";
	    	ResultSet rs=st.executeQuery(sql);
	    	while(rs.next())
	    	{
	    		name=rs.getString("name");
	    		ln=rs.getString("lastname");
	    		id=rs.getString("id");
	    		mail=rs.getString("email");
	    		sem=rs.getInt("semester");
	    		s=new student(name,ln,id,mail,sem);
	    	}
	    	rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return s;
    }
}
