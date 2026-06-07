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
<select id="songSelect" onchange="loadFavUsers(this.value)">
    <option value="">-- Chọn bài hát --</option>
    <c:forEach items="${songs}" var="s"><option value="${s.id}">${s.title}</option></c:forEach>
</select>

<table border="1" width="100%" id="favUsersTable">
    <tr><th>Username</th><th>Fullname</th><th>Email</th><th>Date</th></tr>
    </table>
</body>
</html>