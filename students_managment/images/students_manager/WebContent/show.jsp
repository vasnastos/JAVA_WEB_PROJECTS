<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="javasource.*,java.util.*" %>
<%
request.setCharacterEncoding("utf-8");
	String name=request.getParameter("fname");
    String lastname=request.getParameter("lastname");
	String id=request.getParameter("id");
	String sem=request.getParameter("semester");
	String mail=request.getParameter("mail");
	String tablevisible[]={"ΟΝΟΜΑ","ΕΠΙΘΕΤΟ","ΑΡΙΘΜΟΣ ΜΗΤΡΩΟΥ","E-MAIL","ΕΞΑΜΗΝΟ","ΕΠΕΞΑΡΓΑΣΙΑ","ΔΙΑΓΡΑΦΗ"};
%>

<%
Vector <student> students=database.getInstance().extract();
String action=(String)request.getAttribute("action");
boolean readonlyid=request.getAttribute("readonly")!=null?(boolean)request.getAttribute("readonly"):false;
String buttonname=(String)request.getAttribute("buttonname");
student edited=(student)request.getAttribute("editaction");
%>

<%
  String namevalue=edited!=null?edited.getName():name!=null?name:"";
  String lastnamevalue=edited!=null?edited.getLastname():lastname!=null?lastname:"";
  String idvalue=edited!=null?edited.getId():id!=null?id:"";
  String mailvalue=edited!=null?edited.getEmail():mail!=null?mail:"";
  String semestervalue=edited!=null?String.valueOf(edited.getSemester()):sem!=null?sem:"1";
  String buttonvalue=buttonname!=null?buttonname:"ΑΠΟΘΗΚΕΥΣΗ";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Διαχείριση Μαθητών</title>
<style>
.inputtext
{
  text-align:center;
  border:2px solid;
  margin-bottom:10px;
  margin-right:20xp;
  width:150px;
}

.button
{
  border:1px solid;
  color:red;
  border:radius:8px;
  margin-bottom:30px;
}
.button:hover{
background-color:blue;
color:white;
opacity:0.7;
}

.buttonform
{
  border:1px solid;
  color:red;
  border:radius:8px;
  margin-bottom:30px;
  margin-right:80px;
}
.buttonform:hover{
background-color:blue;
color:white;
opacity:0.7;
}

.buttontable
{
border:1px solid;
  color:red;
  border:radius:8px;
}

.buttontable:hover{
background-color:blue;
color:white;
opacity:0.7;
}

.formstyle {
   margin-bottom:60px;
}

.rowstyle {
 background-color:#53b057;
 color:#b32b19;
 font-size:28 px;
 font-weight:bold;
 text-align:center;
}

.marqueestyle {
color:red;
background-color:lightgray;
width:60%;
font-size:28px;
margin-bottom:30px;
}

.labelstyle {
 color:red;
  margin-right:80px;
  margin-bottom:30px;
   width:700px;
}

.lbstyle
{ color:red;
  margin-right:15px;
  margin-bottom:30px;
   width:700px;
}

.tablestyle {
   width:60%
   background-color:#e1e6cc;
   color:#1d0673;
   border-color:green;
   border:3px solid;
   margin-bottom:100px;
}

.header_row_style
{
 background-color:#be91c2;
 color:#63065c;
 font-size:26px;
}

.backgroundimg
{
 background-image:url('https://github.com/vasnastos/JSP_PROJECTS/blob/main/students_managment/images/database.jpg?raw=true');
 background-repeat:no-repeat;
 background-attachment:fixed;
 background-size:cover;  
}

</style>
</head>
<body class="backgroundimg">
<div style="text-align:center;">
<marquee direction="right" class="marqueestyle" >ΣΥΣΤΗΜΑ ΔΙΑΧΕΙΡΙΣΗΣ ΜΑΘΗΤΩΝ:TED LAB</marquee>
</div>
<div style="text-align:center;">
<form action=handler?action=show method="POST">
<label for="fname" class="labelstyle">ΟΝΟΜΑ</label>
<input type="text" name="fname" class="inputtext" value="<%=namevalue %>" /><br>
<label for="lastname" class="labelstyle">ΕΠΙΘΕΤΟ</label>
<input type="text" name="lastname" class="inputtext" value="<%= lastnamevalue %>" /><br>
<label for="id" class="lbstyle">ΑΡΙΘΜΟΣ ΜΗΤΡΩΟΥ</label>
<input type="text" name="id" value="<%=idvalue%>" class="inputtext" <%if(readonlyid) {%>readonly<%}%>/><br/>
<label for="number" class="labelstyle">ΕΞΑΜΗΝΟ</label>
<input id="number" type="number" min="1" max="32" step="1"  class="inputtext" name="semester" value="<%=semestervalue%>"/><br>
<label for="mail" class="labelstyle">E-MAIL</label>
<input type="text" class="inputtext" name="mail" value="<%=mailvalue %>" /><br><br>
<input type="submit" name="submit" value=<%=buttonvalue %> class="buttonform" style="margin-left:50px;"/>
<input type="submit" name="clear" value="ΚΑΘΑΡΙΣΜΟΣ" class="buttonform"/>
</form>
</div>
<%out.println("<center>"); %>
<table border="3" class="tablestyle">
<tr class="header_row_style">
<%
   for(String x:tablevisible)
   {
%>
<th><%=x %></th>
<%}%>
</tr>
<%
for(student s:students)
{%>
<tr class="rowstyle">
<td><%=s.getName() %></td>
<td><%=s.getLastname() %></td>
<td><%=s.getId() %></td>
<td><%=s.getEmail() %></td>
<td><%=s.getSemester() %></td>
<td>
<form action="handler" method="POST">
<input type="submit" name="edit" value="ΕΠΕΞΕΡΓΑΣΙΑ" class="buttontable"/>
<input type="hidden" name="eid" id="eid" value="<%=s.getId() %>"/>
</form>
</td>
<td>
<form action="handler" method="POST">
<input type="submit" name="delete" value="ΔΙΑΓΡΑΦΗ" class="buttontable"/>
<input type="hidden" name="did" id="did" value="<%=s.getId()%>"/>
</form>
</td>
</tr>
<%}%>
</table>
<%out.println("</center>"); %>
<hr>
<%out.println("<center>"); %>
<marquee direction="right" width="60%" style="color:red; font-size:28px; background-color:#dbbeba;" ><%=action!=null?action:"Μπάρα Ενημέρωσης Αλλαγών"%></marquee>
<%out.println("</center>"); %>
</body>
</html>