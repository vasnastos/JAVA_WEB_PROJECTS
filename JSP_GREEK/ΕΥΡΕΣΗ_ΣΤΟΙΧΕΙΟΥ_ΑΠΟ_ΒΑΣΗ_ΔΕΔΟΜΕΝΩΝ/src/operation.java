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
	  String searchid=request.getParameter("search");
	  RequestDispatcher rd=request.getRequestDispatcher("show_studs.jsp");
	  try {
		request.setAttribute("search", operation.make_accusation(searchid));
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  rd.forward(request,response);
	}

}
