<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="100%">
    <tr>
        <th>Video Title</th>
        <th>Favorite Count</th>
        <th>Latest Date</th>
        <th>Oldest Date</th>
    </tr>
    <c:forEach items="${stats}" var="row">
    <tr>
        <td>${row[0]}</td> <td>${row[1]}</td> <td>${row[2]}</td> <td>${row[3]}</td> </tr>
    </c:forEach>
</table>
</body>
</html>