<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="utf-8"%>

<%@ page import="java.util.*,java.io.*,java.io.BufferedReader,java.util.HashMap,java.math.BigDecimal" %>

<%
	response.setContentType("text/html");
	response.setCharacterEncoding("utf-8");
%>

<%
     HashMap <String,String> coin=new HashMap<String,String>();
     HashMap <String,Integer> currency=new HashMap<String,Integer>();
     double finalconvert=0.0;
     BufferedReader bf=new BufferedReader(new InputStreamReader (new FileInputStream(
     		getServletContext().getRealPath("/WEB-INF/rates.txt")),"UTF-8"));
     String num=bf.readLine();
     while(num!=null)
     {
    	 String data[]=num.split(",");
    	 coin.put(data[1],data[0]);
    	 currency.put(data[0],Integer.parseInt(data[2]));
    	 num=bf.readLine();
     }
     bf.close();
%>

<%
    String fromvalue=request.getParameter("from");
    String tovalue=request.getParameter("to");
    String inputamount=request.getParameter("input");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Μετατροπέας Νομισμάτων</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
 <div style="text-align:center;"><h2 style="color:red; text-align-center; border:collapse;">Μετατροπη Ποσών σε Διαφορετικά Νομίσματα</h2></div>
 <br><br>
 <form>
 <div style="text-align:center">
 Ποσό:
 <%
    out.println("<input type=\"text\" name=\"input\" id=\"input\" value=\""+(inputamount!=null?inputamount:"")+"\"/>");
 %>
 <label for="from" style="border:2px solid;">Απο:</label>
 <select name="from" id="from" style="margin-right:10px;">
 <%
    for(String i:coin.keySet())
    {
    	out.println("<option value=\""+coin.get(i)+"\">"+i+"</option>");
    }
 %>
 </select>
 <label for="to" style="border:2px solid;">Σε:</label>
 <select name="to" id="to" style="margin-right:10px;">
<%
    for(String i:coin.keySet())
    {
    	out.println("<option value=\""+coin.get(i)+"\">"+i+"</option>");
    }
 %>
 </select>
 <input type="submit" value="Μετατροπή" style="border:1px-solid;"/>
 </div>
 </form>
 <br><br>
 <%
     boolean checkvalidance=true;
     double amount=0.0;
     int fcunc=0;
     int tcunc=0;
     try
     {
    	 if(inputamount!=null)
    	amount=Double.parseDouble(inputamount); 
     }catch(NumberFormatException ne)
     {
    	 checkvalidance=false;
     }
     if(!checkvalidance)
     {
    	 out.println("<center><marquee style=\"color:red; font-size:21px;\" width=\"60%\" direction=\"right\">Λαθος Είσοδος:Παρακαλώ δώστε ένα έγκυρο ποσό</marquee>");
     }
     else
     {
    	 String id="";
    	 boolean found1=false,found2=false;
    	 for(String i:currency.keySet())
    	 {
    		 if(i.equals(fromvalue) && !found1)
    		 {
    			 fcunc=currency.get(i);
    			 found1=true;
    		 }
    		 if(i.equals(tovalue) && !found2)
    		 {
    			 tcunc=currency.get(i);
    			 id=i;
    			 found2=true;
    		 }
    		 if(found1 && found2)
    		 {
    			 break;
    		 }
    	 }
    	 finalconvert=(amount*tcunc)/fcunc;
    	 String result=String.format("Result:%3f %s",finalconvert,id);
    	 out.println("<center><textarea name=\"res\" style=\"color:#4a121c; width=100px;\">"+result+"</textarea></center>");
     }
 %>
</body>
</html>