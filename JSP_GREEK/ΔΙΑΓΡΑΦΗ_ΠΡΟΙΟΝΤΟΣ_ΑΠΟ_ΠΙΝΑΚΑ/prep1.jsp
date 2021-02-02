<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ΦΟΡΜΑ ΕΙΣΑΓΩΓΗΣ</title>
<%
request.setCharacterEncoding("utf-8");
 String Table=String.valueOf(request.getAttribute("table"));
%>
<style>
.formtable
{
  border-collapse:collapse;
  border:1px;
  width:40%;
  color:red;
  font-family:callibri;
  font-size:16px;
  margin-right:20px;
  margin-left:250px;
}

.showtable
{
  width:70%;
  font-size:15px;
  text-align:center;
  color:#990000;
  background-color:gray;
}

th
{
  text-decoration:underline;
  font-weight:bold;
  background-color:#b3b3cc;
  color:#990033;
  font-size:19px;
}

td
{
  width:80px;
}

body
{
  background-image:url('https://png.pngtree.com/thumb_back/fh260/background/20191026/pngtree-cosmetic-background-podium-with-wood-texture-for-product-image_320810.jpg');
  background-repeat:no-repeat;
  background-size:cover;
  background-attachment:fixed;
}

.but
{
 color:#1f0e5c;
 background-color:#679c65;
 font-size:15px;
 border-collapse:collapse;
}

.but:hover
{
 color:red;
 background-color:#b8bae3;
}




</style>
<script>
function clear()
{
	var d=document.getElementById("pname")
	var d1=document.getElementById("pid")
	var d2=document.getElementById("pr")
	d.innerHTML=""
	d1.innerHTML=""
	d2.innerHTML=""
}
</script>
</head>
<body>
<center>
<h2 style="background-color:##ff9999; width:40%;">Φόρμα Εισαγωγής προιόντων</h2>
<hr style="color:red; border:2px;">
<form action=handler method="POST">
<table class="formtable">
<tr>
<td><label>ΟΝΟΜΑ ΠΡΟΙΟΝΤΟΣ</label></td>
<td><input type="text" name="pname" id="pr"/></td>
</tr>
<tr>
<td><label>ΑΝΑΓΝΩΡΙΣΤΙΚΟ ΠΡΟΙΟΝΤΟΣ</label></td>
<td><input type="text" name="pid" id="pid"/></td>
</tr>
<tr>
<td><label>ΤΙΜΗ ΠΡΟΙΟΝΤΟΣ</label></td>
<td><input type="text" name="pr" id="pr"/></td>
</tr>
<tr>
<td><hr style="width:50%; float:left;"></td>
<td><hr style="width:50%; float:left;"></td>
</tr>
<tr>
<td><input type="submit" class="but" name="sub" value="ΥΠΟΒΟΛΗ"/></td>
<td><input type="submit" class="but" name="clear" value="ΚΑΘΑΡΙΣΜΟΣ" onclick="clear()"/></td>
</tr>
</table>
</form>
<br>
<br>
<h2 style="width:40%;"><u>ΕΜΦΑΝΙΣΗ ΠΡΟΙΟΝΤΩΝ</u></h2>
<%if(Table!=null) {%>
<%out.println(Table);}%>
</center>
</body>
</html>