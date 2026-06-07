package music;

import java.io.File;
import java.io.IOException;

import Dao.SongDao;
import Model.Song;
import Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet("/add-song")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 15
)
public class AddSongServlet extends HttpServlet {

    private SongDao dao = new SongDao();

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // ===== KIỂM TRA ĐĂNG NHẬP =====
        HttpSession session = request.getSession(false);

        if (session == null ||
            session.getAttribute("user") == null) {

            response.sendRedirect(
                    request.getContextPath() + "/login");
            return;
        }

        User user =
                (User) session.getAttribute("user");

        // ===== LẤY DỮ LIỆU FORM =====
        String title = request.getParameter("title");
        String artist = request.getParameter("artist");
        String genre = request.getParameter("genre");

        // ===== TẠO THƯ MỤC =====
        String imageFolder =
                getServletContext().getRealPath("/images");

        String musicFolder =
                getServletContext().getRealPath("/music");

        new File(imageFolder).mkdirs();
        new File(musicFolder).mkdirs();

        // ===== UPLOAD ẢNH =====
        Part imagePart =
                request.getPart("imageFile");

        String imageName =
                System.currentTimeMillis() + "_"
                + imagePart.getSubmittedFileName();

        imagePart.write(
                imageFolder + File.separator + imageName);

        // ===== UPLOAD NHẠC =====
        Part audioPart =
                request.getPart("audioFile");

        String audioName =
                System.currentTimeMillis() + "_"
                + audioPart.getSubmittedFileName();

        audioPart.write(
                musicFolder + File.separator + audioName);

        // ===== TẠO ĐỐI TƯỢNG SONG =====
        Song song = new Song();

        song.setTitle(title);
        song.setArtist(artist);
        song.setGenre(genre);

        song.setImageUrl("images/" + imageName);
        song.setAudioUrl("music/" + audioName);

        // LƯU NGƯỜI ĐĂNG
        song.setUser(user);

        // ===== LƯU DB =====
        dao.create(song);

        response.sendRedirect(
                request.getContextPath() + "/home");
    }
}