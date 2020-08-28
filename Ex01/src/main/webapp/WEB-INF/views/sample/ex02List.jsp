<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EX02List</title>
</head>
<body>
	<h1><em>EX02List</em></h1>
	<c:forEach var="id" items="${ids}">
		<em>${id}</em>
	</c:forEach>
</body>
</html>