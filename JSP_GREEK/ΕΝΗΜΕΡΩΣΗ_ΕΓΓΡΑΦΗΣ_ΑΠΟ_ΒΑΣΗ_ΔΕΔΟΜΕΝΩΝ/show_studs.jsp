<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Εύρεση Φοιτητή</title>
<%
  String id=String.valueOf(request.getAttribute("selectedid"));
  request.setCharacterEncoding("utf-8");
  String searchfield=String.valueOf(request.getAttribute("search"));
%>
<script>
function selectedId()
{
	var selectedid=document.getElementById("search").value;
    alert('Id Selected:'+selectedid);
}

</script>
<style>
body
{
  background-image:url('https://i.pinimg.com/736x/47/34/3a/47343af83faea5196c3b6cc823b7a2e4.jpg');
  background-repeat:no-repeat;
  background-attachment:fixed;
  background-size:cover;
}

select
{
  border:1px solid;
  font-family:calibri;
  width:20%;
  text-align:center;
  margin-right:50px;
}

label
{
  background-color:#321e42;
  color:#bfe3a1;
  text-align:center;
  width:20%;
  border-collapse:collapse;
  margin-right:50px;
}

.but
{
  color:#06021c;
  background-color:#e8e3b7;
}

.but:hover
{
  color:#5c1c4f;
  background-color:#a8bfa4;
}

</style>
</head>
<body>
<center>
<form action=operation method="POST">
<label>SELECT A STUDENT</label>
<select name="search" id="search">
<%
  String dbUrl="jdbc:mysql://localhost:3306/teddb?serverTimezone=UTC&user=ted&password=ted!";
  Class.forName("com.mysql.jdbc.Driver");
  Connection con=DriverManager.getConnection(dbUrl);
  String sql="select distinct id from student";
  Statement st=con.createStatement();
  st.execute(sql);
  ResultSet rs=st.getResultSet();
  while(rs.next())
  {
%>
<option value="<%=rs.getString(1)%>" <%if(id.equals(rs.getString(1)))  {%>selected<%}%>><%=rs.getString(1) %></option>
<%
 }
  rs.close();
  st.close();
  con.close();
%>
</select>
<input type="submit" class="but" name="sub" onclick="selectedId()" value="ΑΝΑΖΗΤΗΣΗ"/>
<input type="submit" class="but" name="rein" value="ΕΝΗΜΕΡΩΣΗ"/>
</form>
<hr style="border-top:2px blue;">
<br><br>
<%
  if(searchfield!=null)
  {
%>
<h2><u>Search Results</u></h2>
<%
  out.println(searchfield);
  }
%>
</center>
</body>
</html>