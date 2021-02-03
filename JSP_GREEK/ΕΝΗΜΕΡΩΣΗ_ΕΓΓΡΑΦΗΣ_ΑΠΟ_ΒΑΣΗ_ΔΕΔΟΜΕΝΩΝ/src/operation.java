import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class operation
 */
@WebServlet("/operation")
public class operation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public operation() {
        super();
        // TODO Auto-generated constructor stub
    }
    private static String make_accusation(String search) throws ClassNotFoundException, SQLException
    {
    	System.out.println("Search:"+search);
    	String showform="<img src=\"https://cdn0.iconfinder.com/data/icons/kameleon-free-pack-rounded/110/Student-3-512.png\" width=\"20%\" height=\"250px;\"><br><table border=\"2\" style=\"border-collapse:null; background-color:gray; color:blue; width:35%;  font-size:18px;\"><tr><td><label style=\"background-color:#d169c5;\">NAME</label></td><td>";
    	String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
    	  Class.forName("com.mysql.jdbc.Driver");
    	  Connection con=DriverManager.getConnection(dbUrl);
    	  PreparedStatement ps=con.prepareStatement("select * from student where id=?");
    	  ps.setString(1,search);
    	  ps.execute();
    	  ResultSet rs=ps.getResultSet();
    	  if(rs.next())
    	  {
    		  showform+=rs.getString(2)+"</td></tr>"+"<tr><td><label style=\"background-color:#d169c5;\">LASTNAME</label></td><td>"+rs.getString(3)+"</td></tr><tr><td><label style=\"background-color:#d169c5;\">ID</label></td><td>"+rs.getString(1)+"</td></tr><tr><td><label style=\"background-color:#d169c5;\">SEMESTER</label></td><td>"+rs.getString(4)+"</td></tr><tr><td><label style=\"background-color:#d169c5;\">EMAIL</label></td><td>"+rs.getString(5)+"</td></tr>";
    	  }
    	  rs.close();
    	  ps.close();
    	  con.close();
    	  showform+="</table>";
    	  return showform;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	  String searchid=request.getParameter("search");
	  RequestDispatcher rd=request.getRequestDispatcher("show_studs.jsp");
	  String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
	  if(request.getParameter("sub")!=null)	
	  {	try {
	  		request.setAttribute("search", operation.make_accusation(searchid));
	  		} catch (ClassNotFoundException | SQLException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  		request.setAttribute("selectedid",searchid);
	  		rd.forward(request,response);
	  		return;
	  }
	  if(request.getParameter("rein")!=null)
	  {
		  String inf=request.getParameter("search");
		  Connection con=null;
		  try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(dbUrl);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			rd.forward(request,response);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			rd.forward(request,response);
			return;
		}
		  PreparedStatement ps=null;
		try {
			ps = con.prepareStatement("select * from student where id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			ps.setString(1,inf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  ResultSet rs=null;
		  try {
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			if(rs.next())
			  {
				request.setAttribute("iname",rs.getString(2));
				request.setAttribute("iln",rs.getString(3));
				request.setAttribute("iid",rs.getString(1));
				request.setAttribute("isem",rs.getString(4));
				request.setAttribute("imail",rs.getString(5));
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  RequestDispatcher rqd=request.getRequestDispatcher("reinform.jsp");
		  rqd.forward(request,response);
          return;		  
	  }
	  if(request.getParameter("cl")!=null)
	  {
		  RequestDispatcher rqd=request.getRequestDispatcher("reinform.jsp");
		  rqd.forward(request,response);
		  return;
	  }
	  if(request.getParameter("re")!=null)
	  {
		  String name=request.getParameter("renm");
		  String ln=request.getParameter("reln");
		  String id=request.getParameter("reid");
		  int sem=Integer.parseInt(request.getParameter("resem"));
		  String mail=request.getParameter("rem");
		  try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  Connection con=null;
		  try {
			con=DriverManager.getConnection(dbUrl);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  String msg="";
		try {
			PreparedStatement st=con.prepareStatement("update student set name=?,lastname=?,semester=?,email=? where id=?");
			st.setString(1, name);
			st.setString(2,ln);
			st.setInt(3,sem);
			st.setString(4,mail);
			st.setString(5,id);
			if(st.executeUpdate()!=0)
			msg=name+"--"+ln+"--"+id+"--"+mail+"--"+String.valueOf(sem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rqd=request.getRequestDispatcher("reinform.jsp");
		request.setAttribute("result",msg);
		rqd.forward(request, response);
		return;
	  }
	}
	  

}
