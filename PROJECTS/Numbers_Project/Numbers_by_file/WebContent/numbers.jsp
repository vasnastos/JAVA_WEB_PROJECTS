<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="java.util.*,appackage.*"%>
<%
    Numbers n=Numbers.getInstance();
    int counter=0;
    List <List <Double>> nums=n.getList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Numbers Project</title>
<style>
label
{
  margin-right:40px;
  color:red;
  text-decoration:null;
  text-align:center;
}
th
{
  background-color:green;
  color:red;
}
td{
color:blue;
font-size:15px;
}
table
{
   text-align:center;
}
</style>
<script>
function summary(pos,i)
{
    alert('Summary of line '+pos+":"+i);
}
</script>
</head>
<body>
<div style="text-align:center;">
<form action=numberHandler method="post">
<label>ΕΠΙΛΟΓΗ ΑΡΧΕΙΟΥ</label>
<input type="file" id="fn" name="fn"/>
<input type="submit" name="submit" id="submit" value="SUBMIT"/>
</form>
</div>
<br><br>
<%out.println("<center>");%>
<table border="3">
<%if(!n.isEmpty()) {
for(List <Double> l:nums) {%>
<tr>
<% 
for(double num:l)
{
%>
<td>
<%=num%>
</td>
<%}%>
<td>
<form action="numberHandler" method="post">
<input type="submit" onclick="summary(<%=String.valueOf(counter)%>,<%=String.valueOf(n.getSum(counter))%>)"  value="AVERAGE"/>
</form>
</td>
</tr>
<%counter++;}}%>
</table>
<%out.println("</center>");%>
</body>
</html>