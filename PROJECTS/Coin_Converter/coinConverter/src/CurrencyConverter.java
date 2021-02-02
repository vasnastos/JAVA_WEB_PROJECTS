

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CurrencyConverter
 */
@WebServlet("/CurrencyConverter")
public class CurrencyConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static List <concurrency> coins=new ArrayList<concurrency>();
    static
    {
    	coins.add(new concurrency("EUR","Ευρώ",100));
    	coins.add(new concurrency("USD","Δολάριο ΗΠΑ",106));
    	coins.add(new concurrency("GBP","Λίρα Αγγλιας",84));
    	coins.add(new concurrency("AUD","Δολάριο Αυστραλίας",142));
    	coins.add(new concurrency("CAD","Δολάριο Καναδά",139));
    	coins.add(new concurrency("CHF","Φράγκο Ελβετίας",108));
    	coins.add(new concurrency("JPY","Γιεν Ιαπωνίας",12269));
    	coins.add(new concurrency("ALI","Λεκ Αλβανίας",13621));
    	coins.add(new concurrency("CNY","Γουάν Κίνας",732));
    	coins.add(new concurrency("RUB","Ρούβλι Ρωσίας",6487));
    }
    public CurrencyConverter() {
        super();
    }

    private int getConcurency(String id)
    {
    	for(concurrency c:this.coins)
    	{
    		if(c.getDescription().equals(id))
    		{
    			return c.getConcurrency();
    		}
    	}
    	return -1;
    }
    
    private void fill_from_select(PrintWriter pw)
    {
    	for(int i=0;i<coins.size();i++)
    	{
    		pw.println("<option value=\""+coins.get(i).getDescription()+"\">"+coins.get(i).getDescription()+"</option>");
    	}
    }
    
    private void fill_to_select(PrintWriter pw)
    {
    	for(int i=0;i<coins.size();i++)
    	{
    		pw.println("<option value=\""+coins.get(i).getDescription()+"\">"+coins.get(i).getDescription()+"</option>");
    	}
    }
    
    private double convert(String from,String to,double amount)
    {
    	if(from.equals(to))
    	{
    		return amount;
    	}
    	int fromtransaction=this.getConcurency(from);
   	    int totransaction=this.getConcurency(to);
   	    return (amount*totransaction)/fromtransaction;
    }
    
    private String find_coin(String id)
    {
    	for(concurrency c:this.coins)
    	{
    		if(c.getDescription().equals(id))
    		{
    			return c.getCoin();
    		}
    	}
    	return "";
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String fromvalue=null,tovalue=null,amountinput=null;
		PrintWriter pw=response.getWriter();
		amountinput=request.getParameter("price");
	    fromvalue=request.getParameter("from");
	    tovalue=request.getParameter("to");
		pw.println("<html><body style=\"bgcolor=\"#97c9ab;\"\">");
		pw.println("<center><h2>Μετατροπή Ποσών σε Διαφορετικά Νομίσματα</h2><br>");
		String form="<form><center>Ποσό:<input type=\"text\" name=\"price\" value=\""+(amountinput!=null?amountinput:"")+"\"/>"
				+"<label for=\"from\" style=\"border:1px solid;\">Από:</label><select name=\"from\" id=\"from\">";
		pw.println(form);
		this.fill_from_select(pw);
		pw.println("</select>");
		pw.println("<label for=\"to\" style=\"border:2px solid;\">Σε:</label><select name=\"to\" id=\"to\">");
		this.fill_to_select(pw);
		pw.println("</select>");
		pw.println("<input type=\"submit\" value=\"Μετατροπή\"/>");
		pw.println("</form>");
		pw.println("<br><br><br><center>");
		boolean checkinput=true;
	    double amount=0;
	    try
	    {
	    	amount=Double.parseDouble(amountinput);
	    }catch(NumberFormatException ne)
	    {
	    	checkinput=false;
	    }
	    //checkpoint for amount
	    if(!checkinput)
	    {
	    	pw.println("<textarea id=\"res\" name=\"res\" style=\"width=30%; border:2px solid; text-align:center;\" rows=\"1\" cols=\"50\">Παρακαλώ εισάγεται έναν πραγματικό αριθμό</textarea>");
	    	pw.println("</body></html>");
	    	return;
	    }
	    double res=convert(fromvalue,tovalue,amount);
	    String coin=this.find_coin(tovalue);
	    String sres=String.format("Result:%.3f %s",res,coin);
	    pw.println("<textarea id=\"res\" name=\"res\" style=\"width=30%; border:2px solid; text-align:center;\" rows=\"1\" cols=\"50\">"+sres+"</textarea>");
	    pw.println("</center></body></html>");
      }
}
