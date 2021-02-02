package preperation_1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class handler
 */
@WebServlet("/handler")
public class handler extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static List <product> products=new ArrayList<product>();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public handler() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
    private static void printProducts()
    {
    	System.out.println("\tProducts");
    	System.out.println("*********************************************");
    	for(product p:products)
    	{
    		System.out.println(p);
    	}
    }
    private static String makeTable()
    {
    	String msh="<table border=\"2\" class=\"showtable\"><tr><th>NAME</th><th>ID</th><th>PRICE</th><th>ΔΙΑΓΡΑΦΗ</th></tr>";
    	for(product p:products)
    	{
    		msh+="<tr><td>"+p.getName()+"</td><td>"+p.getId()+"</td><td>"+String.valueOf(p.getPrice())+"</td><td><form action=handler method=\"POST\"><input type=\"submit\" class=\"but\" name=\"del\" value=\"ΔΙΑΓΡΑΦΗ\"/><input type=\"hidden\" name=\"id\" value=\""+p.getId()+"\"/></form></td></tr>";
    	}
    	msh+="</table>";
    	return msh;
    }
    private static void deleteValue(String id)
    {
    	System.out.println("Eration made");
    	int index=0;
    	for(product p:products)
    	{
    		if(p.getId().equals(id))
    		{
    			products.remove(index);
    			return;
    		}
    		index++;
    	}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		RequestDispatcher rd=request.getRequestDispatcher("prep1.jsp");
		if(request.getParameter("sub")!=null)
		{
			String productname=request.getParameter("pname");
			String productid=request.getParameter("pid");
			String productprice=request.getParameter("pr");
			if(productname.isEmpty() || productid.isEmpty() || productprice.isEmpty() || request.getParameter("clear")!=null)
			{
				rd.forward(request,response);
				request.setAttribute("table",handler.makeTable());
				return;
			}
			product p=new product(productname,productid,Double.parseDouble(productprice));
			if(products.contains(p))
			{
				System.out.println("Product "+productid+" is in the list");
			}
			else
			{
				products.add(p);
				request.setAttribute("table",handler.makeTable());
			}
			handler.printProducts();
		}
		if(request.getParameter("del")!=null)
		{
			String deletionId=request.getParameter("id");
			handler.deleteValue(deletionId);
		}
		request.setAttribute("table",handler.makeTable());
		rd.forward(request, response);
	}

}
