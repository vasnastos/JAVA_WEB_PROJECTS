package appackage;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resources;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class numberHandler
 */
@WebServlet("/numberHandler")
public class numberHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public numberHandler() {
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
		request.setCharacterEncoding("utf-8");
		String file=request.getParameter("fn");
		Numbers n=Numbers.getInstance();
		String buttonsubmit=request.getParameter("submit");
		if(buttonsubmit!=null)
		{
			System.out.println(file);
			n.clear();
			File myfile=new File(file);
			BufferedReader br=new BufferedReader(new FileReader(myfile.getCanonicalFile().getAbsolutePath()));
			String line=br.readLine();
			while(line!=null)
			{
				String data[]=line.split(",");
				List <Double> mylist=new ArrayList<Double>();
				for(String x:data)
				{
					mylist.add(Double.parseDouble(x));
				}
				n.add(mylist);
				line=br.readLine();
			}
		}
		RequestDispatcher rd=request.getRequestDispatcher("numbers.jsp");
		rd.forward(request, response);
	}

}
