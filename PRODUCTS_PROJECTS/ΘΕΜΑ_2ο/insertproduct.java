

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class insertproduct
 */
@WebServlet("/insertproduct")
public class insertproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public insertproduct() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inid=request.getParameter("pid");
		String inname=request.getParameter("pname");
		String inprice=request.getParameter("pprice");
		String submitbutton=request.getParameter("sub");
		System.out.println("**********************************");
		if(submitbutton!=null)
		{
			Connection con=null;
			String url="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
			}
			try {
				con=DriverManager.getConnection(url);
			} catch (SQLException e) {}
			try {
				PreparedStatement ps=con.prepareStatement("insert into product(pid,pname,price) values(?,?,?)");
				ps.setString(1,inid);
				ps.setString(2,inname);
				ps.setDouble(3,Double.parseDouble(inprice));
				ps.executeUpdate();
				ps.close();
				con.close();
				System.out.println("An element inserted on the database");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("theme1.html");
	}

}
