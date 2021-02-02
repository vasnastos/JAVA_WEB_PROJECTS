package appackage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public handler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String filename=request.getParameter("selfile");
       String subutton=request.getParameter("ran");
       if(filename!=null && !filename.isEmpty())
       {
    	   File f=new File(filename);
    	   FileReader fr=new FileReader(f.getAbsoluteFile().getAbsolutePath());
    	   BufferedReader br=new BufferedReader(fr);
    	   String line=br.readLine();
    	   while(line!=null)
    	   {
    		   String data[]=line.split(",");
    		   numbers.add_number(new SystemShuffler(Integer.parseInt(data[0]),data[1]));
    		   line=br.readLine();
    	   }
    	   br.close();
       }
       if(subutton!=null)
       {
    	   numbers.random_fill();
       }
       RequestDispatcher rd=request.getRequestDispatcher("numbers.jsp");
       rd.forward(request,response);
	}

}
