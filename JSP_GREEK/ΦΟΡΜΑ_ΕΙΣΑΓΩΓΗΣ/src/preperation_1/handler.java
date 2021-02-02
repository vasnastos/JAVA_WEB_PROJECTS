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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productname=request.getParameter("pname");
		String productid=request.getParameter("pid");
		String productprice=request.getParameter("pr");
		if(productname.isEmpty() || productid.isEmpty() || productprice.isEmpty() || request.getParameter("clear")!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("prep1.html");
			rd.forward(request,response);
			return;
		}
		product p=new product(productname,productid,Double.parseDouble(productprice));
		if(products.contains(p))
		{
			System.out.println("Product "+productid+" is in the list");
		}
		else
		products.add(p);
		handler.printProducts();
		RequestDispatcher rd=request.getRequestDispatcher("prep1.html");
		rd.forward(request, response);
	}

}
