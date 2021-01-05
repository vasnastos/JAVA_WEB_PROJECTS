<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ page import="appackage.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Random Numbers</title>
<style>
table{
   margin-top:35px;
   text-align:center;
   font-size:15px;
   background-color:lightgray;
   color:blue;
   width:70%;
   font-weight:bold;
   margin-bottom:15px;
}

th{
  background-color:green;
  color:red;
  font-size:21px;
  text-decoration:none;
  font-family:calibri;
  font-weight:italics;
}

marquee {
   color:red;
   font-size:15px;
   background-color:lightgray;
   width:60%;
   margin-bottom:35px;
}

label {
 margin-right:25px;
 margin-left:100px;
 border:2px solid;
}

.input {
  color:blue;
  background-color:white;
  border:1px solid;'
}

.input:hover
{
   background-color:#7cc48f;
}

</style>
</head>
<body>
<%out.println("<center>"); %>
<marquee direction="right">Random Numbers Shuffler</marquee>
<br>
<form action=handler method="post">
<label>SELECT AN IMPORT FILE</label>
<input type="file" name="selfile" style="color:red; margin-right:35px;" value="IMPORT"/>
<input type="submit" name="ran" value="Randomize"/>
</form>
<table border="3">
<tr>
<th>SYSTEM</th>
<th>NUMBER</th>
</tr>
<%out.println(numbers.print_table());%>
</table>
<br>
<%
	out.println(numbers.print());
	out.println("</center>");
%>
</body>
</html>