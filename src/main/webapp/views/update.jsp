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
<p style="color: black; background-color: aqua;">${status}</p>

 <form action="updategarage">
 
 Select ID: <select name="gid">
 <c:forEach items="${ids}" var="x">
 <option>${x}</option>
 </c:forEach>
</select>
<br><br>
New Garage Name : <input type="text" name="name" required="required"><br><br>
New Garage Place : <input type="text" name="place" required="required"><br><br>
                   <button>Update</button>
 </form>
 
 <hr>
 <hr>

</body>
</html>