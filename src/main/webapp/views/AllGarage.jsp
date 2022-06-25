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
<h3 style="color: black;background-color: aqua;">So many Garage</h3>
<hr>
<hr>
<c:forEach items="${getall}" var="x">
Garage ID: <c:out value="${x.gid}"></c:out><br><br>
Garage Name: <c:out value="${x.name}"></c:out><br><br>
Garage Place: <c:out value="${x.place}"></c:out><br><br>
         
         <img alt="" src="getimage?gid=${x.gid}" height="50px" width="50px"><br><br>
         
         <form action="Updateimage"  method="post" enctype="multipart/form-data">
          <input type="hidden" name="gid" value="${x.gid}">
Update Image : <input type="file" name="image"><br><br>
               <button>Upload</button>
    </form>
<hr>
<hr>
</c:forEach>
<hr>
<hr>
</body>
</html>