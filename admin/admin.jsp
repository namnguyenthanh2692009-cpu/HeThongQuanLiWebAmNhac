<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Tool - Black Cat Music</title>
    <style>
        .tab-content { padding: 20px; border: 1px solid #ccc; }
        .sub-tabs { margin-top: 10px; }
    </style>
</head>
<body>
    <div class="admin-tabs">
    <button onclick="openTab('song-tab')">Quản lý Bài hát</button>
    <button onclick="openTab('report-tab')">Báo cáo & Thống kê</button>
</div>

<div id="report-tab" class="tab-content" style="display:none;">
    <jsp:include page="/admin/reports_management.jsp"/>
</div>

    <div id="song-tab" class="tab-content">
        <div class="sub-tabs">
            <button onclick="switchSubTab('edit')">Song Edition</button>
            <button onclick="switchSubTab('list')">Song List</button>
        </div>

        <div id="edit-area">
            <form id="songForm">
                <input type="hidden" id="songIdHidden" name="id">
                <input type="text" name="title" placeholder="Song Title?">
                <input type="text" name="artist" placeholder="Artist?">
                <input type="text" name="genre" placeholder="Genre?">
                <button type="button" onclick="handleSongAction('create')">Create</button>
                <button type="button" onclick="handleSongAction('update')">Update</button>
                <button type="button" onclick="handleSongAction('delete')">Delete</button>
            </form>
        </div>

        <div id="list-area" style="display:none;">
            <table>
                <tr><th>Title</th><th>Artist</th><th>Action</th></tr>
                <c:forEach items="${songs}" var="s">
                    <tr>
                        <td>${s.title}</td>
                        <td>${s.artist}</td>
                        <td><button onclick="loadSongToForm('${s.id}')">Edit</button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <script>
        function openTab(tabName) {
            document.querySelectorAll('.tab-content').forEach(el => el.style.display = 'none');
            document.getElementById(tabName).style.display = 'block';
        }

        function switchSubTab(tab) {
            document.getElementById('edit-area').style.display = (tab === 'edit') ? 'block' : 'none';
            document.getElementById('list-area').style.display = (tab === 'list') ? 'block' : 'none';
        }

        function loadSongToForm(id) {
            switchSubTab('edit');
            document.getElementById('songIdHidden').value = id;
        }

        function handleSongAction(action) {
            const formData = new FormData(document.getElementById('songForm'));
            fetch('AdminController?action=' + action, {
                method: 'POST',
                body: formData
            }).then(() => {
                alert("Thực hiện thành công!");
                location.reload();
            });
        }
    </script>
</body>
</html>