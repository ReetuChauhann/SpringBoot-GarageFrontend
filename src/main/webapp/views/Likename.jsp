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
<h3 style="color:black; background-color:aqua;">Like Name Garage!</h3>
<form action="likenamegarage">
Enter the name: <input type="text" name="name" required="required">
                <button>Print</button>
</form>
<hr>
<hr>
<c:forEach items="${likename}" var="y">
Garage ID: <c:out value="${y.gid}"></c:out><br><br>
Garage Name: <c:out value="${y.name}"></c:out><br><br>
Garage Place: <c:out value="${y.place}"></c:out><br><br>
         
         <img alt="" src="getimage?gid=${y.gid}" height="50px" width="50px"><br><br>
</c:forEach>
<hr>
<hr>

</body>
</html>