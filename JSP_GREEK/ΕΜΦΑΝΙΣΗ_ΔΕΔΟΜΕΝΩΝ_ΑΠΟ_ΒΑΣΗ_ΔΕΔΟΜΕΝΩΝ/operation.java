import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    private static void showData(PrintWriter pw) throws SQLException
    {
    	String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	pw.println("<table border=\"2\"><tr><th>Name</th><th>Lastname</th><th>Id</th><th>Semester</th><th>E-mail</th></tr>");
    	Connection con=DriverManager.getConnection(dbUrl);
    	Statement st=con.createStatement();
    	st.execute("select * from student");
    	ResultSet rs=st.getResultSet();
    	while(rs.next())
    	{
    		pw.println("<tr><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(1)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
    	}
    	pw.println("</table>");
    	rs.close();
    	st.close();
    	con.close();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		pw.println("<html><head><title>ΕΜΦΑΝΙΣΗ ΦΟΙΤΗΤΩΝ</title><style>table{color:red; background-color:gray; font-weight:bold; font-size:15px; width:70%;} th{background-color:blue; color:white; font-size:white;} body{background-image:url('https://i.pinimg.com/736x/47/34/3a/47343af83faea5196c3b6cc823b7a2e4.jpg'); background-repeat:no-repeat; background-attchment:fixed; background-size:cover;}</style></head><body>");
		pw.println("<center><h2><u>Εμφάνιση Φοιτητών</u></h2><hr style=\"border-top:2px solid red;\">");
		try {
			operation.showData(pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("</center></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	}

}
