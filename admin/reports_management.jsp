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
<div class="sub-tabs">
    <button onclick="switchReportTab('favorites')">Favorites</button>
    <button onclick="switchReportTab('fav-users')">Favorite Users</button>
    <button onclick="switchReportTab('shared')">Shared Friends</button>
</div>

<div id="report-favorites" class="report-area">
    <table>
        <tr><th>Video Title</th><th>Favorite Count</th><th>Latest Date</th><th>Oldest Date</th></tr>
        </table>
</div>

<div id="report-fav-users" class="report-area" style="display:none;">
    <select onchange="loadFavUsers(this.value)">
        <c:forEach items="${songs}" var="s"><option value="${s.id}">${s.title}</option></c:forEach>
    </select>
    <table>
        <tr><th>Username</th><th>Fullname</th><th>Email</th><th>Date</th></tr>
    </table>
</div>

<div id="report-shared" class="report-area" style="display:none;">
    <select onchange="loadSharedFriends(this.value)">
        <c:forEach items="${songs}" var="s"><option value="${s.id}">${s.title}</option></c:forEach>
    </select>
    <table>
        <tr><th>Sender</th><th>Sender Email</th><th>Receiver Email</th><th>Date</th></tr>
    </table>
</div>

<script>
function switchReportTab(tab) {
    document.querySelectorAll('.report-area').forEach(el => el.style.display = 'none');
    document.getElementById('report-' + tab).style.display = 'block';
}
</script>
</body>
</html>