package Dao;
import Model.Favorite;
import jakarta.persistence.*;
import utils.JpaUtil;

import java.util.List;

public class ReportDao {
	public List<Object[]> getFavoriteStats() {
        EntityManager em = JpaUtil.getEntityManager();
        // Câu lệnh SQL nhóm dữ liệu theo bài hát
        String jpql = "SELECT f.song.title, COUNT(f.id), MAX(f.likedDate), MIN(f.likedDate) " +
                      "FROM Favorite f GROUP BY f.song.title";
        return em.createQuery(jpql).getResultList();
    }
}