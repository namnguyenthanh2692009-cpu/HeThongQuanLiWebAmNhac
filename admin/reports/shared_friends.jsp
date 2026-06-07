<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
</head>
<body>
<select id="sharedSelect" onchange="loadSharedFriends(this.value)">
    <option value="">-- Chọn bài hát --</option>
    <c:forEach items="${songs}" var="s"><option value="${s.id}">${s.title}</option></c:forEach>
</select>

<table border="1" width="100%" id="sharedTable">
    <tr><th>Sender</th><th>Sender Email</th><th>Receiver Email</th><th>Sent Date</th></tr>
</table>
</body>
</html>