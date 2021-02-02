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
    	String msh="<table border=\"2\" class=\"showtable\"><tr><th>NAME</th><th>ID</th><th>PRICE</th></tr>";
    	for(product p:products)
    	{
    		msh+="<tr><td>"+p.getName()+"</td><td>"+p.getId()+"</td><td>"+String.valueOf(p.getPrice())+"</td></tr>";
    	}
    	msh+="</table>";
    	return msh;
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String productname=request.getParameter("pname");
		String productid=request.getParameter("pid");
		String productprice=request.getParameter("pr");
		if(productname.isEmpty() || productid.isEmpty() || productprice.isEmpty() || request.getParameter("clear")!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("prep1.jsp");
			rd.forward(request,response);
			return;
		}
		RequestDispatcher rd=request.getRequestDispatcher("prep1.jsp");
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
		rd.forward(request, response);
	}

}
