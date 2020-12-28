

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Employee_Base
 */
@WebServlet("/Employee_Base")
public class Employee_Base extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PrintWriter  out;
    private database mydb=null;
    String redirect="Employee_Base";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Employee_Base() {
        super();
        // TODO Auto-generated constructor stub
    }
    private void header()
    {
    	out.println("<center><h2>EMPLOYEES MANAGMENT SYSTEM</h2></center>");
    }
    private void printform() throws SQLException
    {
    	out.println("<center>");
    	out.println("<form method=\"POST\">");
    	out.println("<table style=\"border:null; width=100%; margin-bottom:40px;\">");
    	out.println("<tr>");
    	out.println("<th></th><th></th></tr>");
    	out.println("<tr><td><label style=\"margin-right:40px; border:2px solid;\">NAME</label></td>");
    	out.println("<td><input type=\"text\" name=\"fn\"/></td></tr>");
    	out.println("<tr><td><label style=\"margin-right:40px; border:2px solid;\">DEPARTMENT</label></td>");
    	out.println("<td><select id=\"dep\">");
    	Vector <String> categories=mydb.departments();
    	if(!categories.isEmpty())
    	{
    		for(String x:categories)
    		{
    			out.println("<option value=\""+x+"\">"+x+"</option>");
    		}
    	}
    	out.println("</select></td></tr>");
    	out.println("<tr><td><label style=\"margin-right:40px; border:2px solid;\">SALARY</label></td>");
    	out.println("<td><input type=\"text\" name=\"salary\"></td></tr>");
    	out.println("<tr><td><label style=\"margin-right:40px; border:2px solid;\">CV FILE</label></td>");
    	out.println("<td><input type=\"file\" name=\"myfile\" value=\"Upload CV\"/>");
    	out.println("</table>");
    	out.println("<input type=\"Submit\" style=\"background-color:#b9e8b0; color:#770382; border:1px solid;\" name=\"submit\" onmouseover=\"fhover()\" value=\"SUBMIT\"><td></tr>");
    	out.println("</form>");
    	out.println("</center>");
    }
    private void PrintTable() throws SQLException
    {
    	out.println("<center>");
    	out.println("<table border=\"3\" style=\"margin-bottom:40px; margin-top:45px; width=100%; height=auto; font-family:calibri; font-size:15px; color:red; background-color:white;\">");
    	out.println("<tr><th>Fullname</th><th>Department</th><th>Salary</th><th>BioLink</th></tr>");
    	List <employee> emps=mydb.employees();
    	for(employee e:emps)
    	{
    		out.println("<tr><td>"+e.name+"</td><td>"+e.depid+"</td><td>"+String.valueOf(e.salary)+"</td><td>");
    		out.println("<td><a href=\"\"><img src=\"https://images.vexels.com/media/users/3/140030/isolated/preview/521136d25b37386f49728b93d2e4e6fa-cv-icon-by-vexels.png\" width=\"40px;\" height=\"40px;\"/></a></td></tr>");
    		out.println("<form action=\"biocv.jsp\"><input type=\"hidden\" name=\"cv\" id=\"cv\" value=\""+e.name+"></form>");
    	}
    	out.println("</table></center>");
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		out=response.getWriter();
		try {
			mydb=database.getInstance();
		} catch (ClassNotFoundException e1) {
			System.out.println("Database Class Do not found");
		} catch (SQLException e1) {
			System.out.println("Error in Query");
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<script>");
		out.println("function fhover() {var elem=document.getElementById(\"cv\") elem.style.color:\"red\" elem.style.backgroundcolor=\"green\";}");
		out.println("</script>");
		out.println("<style>");
		out.println("tr{margin-bottom:15px; color:blue;}");
		out.println("</style>");
		out.println("/<head>");
		out.println("<body>");
		this.header();
		try {
			this.printform();
		} catch (SQLException e) {
			System.out.println("Sql Error in table employee");
		}
		try {
			this.PrintTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   String buttonid=request.getParameter("submit");
	   String name=request.getParameter("fn");
	   String department=request.getParameter("dep");
	   String salary=request.getParameter("salary");
	   String filename=request.getParameter("myfile");
	   if(buttonid!=null)
	   {
		   FileReader fr=new FileReader(filename);
		   BufferedReader rd=new BufferedReader(fr);
		   String line=rd.readLine();
		   String htmlsource=line;
		   while(line!=null)
		   {
			   line=rd.readLine();
			   htmlsource+=line;
		   }
		   rd.close();
		   try {
			mydb.insertEmployee(name,department, Double.parseDouble(salary),htmlsource);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   response.sendRedirect(redirect);
	}

}
