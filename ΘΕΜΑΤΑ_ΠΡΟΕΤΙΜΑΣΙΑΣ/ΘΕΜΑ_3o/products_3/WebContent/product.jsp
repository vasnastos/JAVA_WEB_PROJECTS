<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<%@page import="products_3.*,java.util.*" %>
<!DOCTYPE html>
<%
   Vector <product> prds=(Vector <product>)request.getAttribute("container");
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PRODUCT</title>
<style>
h2
{
  background-color:black;
  color:white;
  height:auto;
  width=30%;
}

th{
 background-color:green;
 color:red;
}
td
{
  background-color:lightgray;
  color:blue;
}
</style>
</head>
<body>
<center>
<h2>Board of Database Contents</h2>
<table border="3">
<tr>
<th>ID</th>
<th>NAME</th>
<th>PRICE</th>
</tr>
<%
for(product p:prds)
{%>
<tr>
<td><%=p.id %></td>
<td><%=p.name %></td>
<td><%=String.valueOf(p.price) %></td>
</tr>
</table>
<%}%>
</center>
</body>
</html>