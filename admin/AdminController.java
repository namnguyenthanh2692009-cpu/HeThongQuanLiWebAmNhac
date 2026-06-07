package admin;


import Dao.ReportDao;
import Dao.SongDao;
import Model.Song;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private SongDao songDao = new SongDao();

    // doGet dùng để hiển thị giao diện Admin
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    // 1. Kiểm tra quyền
	    HttpSession session = request.getSession();
	    Model.User user = (Model.User) session.getAttribute("user");
	    
	    if (user == null || !"ADMIN".equals(user.getRole())) {
	        response.sendRedirect(request.getContextPath() + "/home");
	        return;
	    }

	    // 2. Khởi tạo DAO
	    ReportDao reportDao = new ReportDao();
	    SongDao songDao = new SongDao(); // Đảm bảo bạn đã khởi tạo songDao

	    // 3. Nạp tất cả dữ liệu cần thiết vào request
	    request.setAttribute("songs", songDao.findAll());
	    request.setAttribute("stats", reportDao.getFavoriteStats());
	    
	    // 4. Chỉ forward duy nhất 1 lần cuối cùng sau khi đã nạp đủ dữ liệu
	    request.getRequestDispatcher("/admin/admin.jsp").forward(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        try {
            if ("create".equals(action)) {
                Song s = new Song();
                s.setTitle(request.getParameter("title"));
                s.setArtist(request.getParameter("artist"));
                s.setGenre(request.getParameter("genre"));
                songDao.create(s);
            } else if ("update".equals(action)) {
                Song s = songDao.findById(Long.parseLong(idStr));
                s.setTitle(request.getParameter("title"));
                s.setArtist(request.getParameter("artist"));
                s.setGenre(request.getParameter("genre"));
                songDao.update(s);
            } else if ("delete".equals(action)) {
                songDao.delete(Long.parseLong(idStr));
            }
            response.setContentType("application/json");
            response.getWriter().write("{\"status\":\"success\"}");
        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"status\":\"error\"}");
        }
    }
}