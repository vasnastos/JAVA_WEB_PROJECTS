<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<%
   class product
   {
	  public String pid;
	  public String pname;
	  public double price;
	  public product(String id,String n,double p)
	  {
		  this.pid=id;
		  this.pname=n;
		  this.price=p;
	  } 
   }
   List <product> products=new ArrayList<product>();
   Class.forName("com.mysql.jdbc.Driver");
   String url="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
   Connection con=DriverManager.getConnection(url);
   Statement st=con.createStatement();
   ResultSet rs=st.executeQuery("select * from product");
   while(rs.next())
   {
	   products.add(new product(rs.getString(1),rs.getString(2),rs.getDouble(3)));
   }
   rs.close();
   st.close();
   con.close();
   
%>
<meta charset="ISO-8859-1">
<title>Products</title>
<style>
th {
   background-color:green;
   color:blue;
   font-size:18px;
}
table {
  background-color:lightgray;
  color:purple;
  font-weight:bold;
  width:50%;
}
</style>
</head>
<body>
<center>
<h2>Table of Contents</h2>
<table border="3">
<tr><th>PRODUCT ID</th><th>PRODUCT NAME</th><th>PRODUCT PRICE</th></tr>
<%
for(product p:products)
{%>
<tr>
<td><%=p.pid%></td>
<td><%=p.pname %></td>
<td><%=String.valueOf(p.price)%></td>
</tr>
<%} %>
</table>
</center>
</body>
</html>