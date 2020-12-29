

import java.io.IOException;
import java.io.PrintWriter;
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
    private PrintWriter out=null;
    private database db;
    private String marqueeval="";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public insertproduct() {
        super();
        // TODO Auto-generated constructor stub
    }

    private void PrintForm()
    {
    	out.println("<center>");
    	out.println("<form method=\"post\">");
    	out.println("<table><tr><th></th><th></th></tr>");
    	out.println("<tr><td><label>ID</label></td><td>");
    	out.println("<input type=\"text\" name=\"pid\"/></td></tr>");
    	out.println("<tr><td><label>NAME</td><td>");
    	out.println("<input type=\"text\" name=\"pname\"></td></tr>");
    	out.println("<tr><td><label>PRICE</label></td><td>");
    	out.println("<td><input type=\"text\" name=\"pprice\"></td></tr>");
    	out.println("</table><input type=\"submit\" name=\"sub\" value=\"SUBMIT\"/>");
    	out.println("</form>");
    	out.println("<br><br><br>");
    	out.println("<marquee direction=\"right\" style=\"width:60% color=red;\">"+marqueeval+"</marquee>");
    	out.println("</center>");
    	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		out=response.getWriter();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<body>");
		this.PrintForm();
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			db=database.getInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String idparam=request.getParameter("pid");
		String nameparam=request.getParameter("pname");
		String priceparam=request.getParameter("pprice");
		String buttonpressed=request.getParameter("sub");
		if(buttonpressed!=null)
		{
			double aprice=-1.0;
			try{
				Double.parseDouble(priceparam);
			}catch(NumberFormatException ae)
			{
				marqueeval="Error on price value";
				try {
					db.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				marqueeval=db.insert(nameparam,idparam,aprice);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				db.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
