package javasource;


import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet implementation class handler
 */
@WebServlet("/handler")
public class handler extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public handler() {
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
		String centralbutton=request.getParameter("submit");
		String editbutton=request.getParameter("edit");
		String delbutton=request.getParameter("delete");
		String name=request.getParameter("fname");
		String lname=request.getParameter("lastname");
		String email=request.getParameter("mail");
		String id=request.getParameter("id");
		String clearbutton=request.getParameter("clear");
		int se;
	    try
	    {
	    	se=Integer.parseInt(request.getParameter("semester"));
	    }
	    catch(NumberFormatException ne)
	    {
	    	se=-1;
	    }
		if(centralbutton!=null && centralbutton.equals("ΑΠΟΘΗΚΕΥΣΗ"))
		{
			String message="";
		    if(se==-1)
		    {
		    	message="Unknown semester type:"+request.getParameter("semester");
		    }
		    else
		    {
		    	student newstudent=new student(name,lname,id,email,se);
		    	message="Τελευταία Εισαγωγή:"+newstudent.to_str();
		    	database.getInstance().insert(name, lname, id, email, se);
		    }
		    HttpSession session=request.getSession();
		    request.setAttribute("action",message);
		    request.setAttribute("buttonname","ΑΠΟΘΗΚΕΥΣΗ");
		    request.setAttribute("readonly",false);
		    RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
		    rd.forward(request,response);
		}
		else if(centralbutton!=null && centralbutton.equals("ΕΝΗΜΕΡΩΣΗ"))
		{
			String message;
			request.setAttribute("buttonname","ΑΠΟΘΗΚΕΥΣΗ");
			if(se==-1)
			{
				message="Unknown semester type:"+request.getParameter("Semester");
				RequestDispatcher rd=request.getRequestDispatcher("central.jsp");
				rd.forward(request,response);
			}
			else 
			{
				message="Τελευταία ενημερωμένη εγγραφή:"+database.getInstance().update_record(id, se, name, lname, email);
			    request.setAttribute("action",message);
			    request.setAttribute("readonly",false);
				RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
				rd.forward(request,response);
			}
		}
		else if(editbutton!=null)
		{
			String editid=request.getParameter("eid");
			student editstudent=database.getInstance().search(editid);
			request.setAttribute("editaction",editstudent);
			request.setAttribute("buttonname","ΕΝΗΜΕΡΩΣΗ");
			request.setAttribute("readonly",true);
			RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
			rd.forward(request,response);
		}
		else if(delbutton!=null)
		{
			String delid=request.getParameter("did");
			String message="Διαγραφή ΦΟΙΤΗΤΗ:"+database.getInstance().delete(delid);
			request.setAttribute("readonly",false);
		    request.setAttribute("action",message);
			RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
			rd.forward(request,response);
		}
		else if(clearbutton!=null)
		{
			student editstudent=new student();
			request.setAttribute("editaction",editstudent);
			RequestDispatcher rd=request.getRequestDispatcher("show.jsp");
			rd.forward(request,response);
		}
		else 
		{
			System.out.println("Error on post method");
		}
	}

}
