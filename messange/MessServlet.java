package messange;

import java.io.IOException;

import Dao.MessDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/notifications")
public class MessServlet extends HttpServlet {

    private MessDao dao =
            new MessDao();

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "notifications",
                dao.findAll());

        request.getRequestDispatcher(
                "/user/Mess.jsp")
                .forward(request, response);
    }
}
