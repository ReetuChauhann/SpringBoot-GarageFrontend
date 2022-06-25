<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3 style="color:black; background-color:aqua;">Garage APP</h3>
<hr>
<hr>
<h3 style="color:black; background-color:aqua;">${sts}</h3>
<form action="addgarage" method="post" enctype="multipart/form-data">
Garage ID: <input type="number" name="gid" placeholder="Enter the Garage Id Plz!"><br><br>
Garage Name: <input type="text" name="name" placeholder="Enter the Garage Name Plz!"><br><br>
Garage Location: <input type="text" name="place" placeholder="Enter the Garage Location!"><br><br>
Garage Image: <input type="file" name="image"><br><br>
               <button style="color: black;background-color:aqua; ">ADD</button>

</form>
<hr>
<hr>
<a href="GetallGarage" style="color: black; background-color: aqua;">View All Garage</a>
<hr>
<hr>
<a href="updateG" style="color: black; background-color: aqua;" >Update All</a>
<hr>
<hr>
<h3 style="color: black;background-color: aqua;">Select the Id of the Garage You Want to delete!</h3>
<p style="color: black; background-color: aqua;">${sts}</p>
<form action="Deletebyid">
Select ID: <select name="gid">
 <c:forEach items="${ids}" var="x">
 <option>${x}</option>
 </c:forEach>
</select>
<button>Delete</button>
</form>
<hr>
<hr>
<a href="likename">All Same Name Garage</a>
<hr>
<hr>
</body>
</html>