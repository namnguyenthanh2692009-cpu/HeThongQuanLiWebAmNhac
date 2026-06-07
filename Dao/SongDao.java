package Dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import Model.Song;
import utils.JpaUtil;

public class SongDao {

    public List<Song> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Song s",
                    Song.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Song> search(String keyword) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            TypedQuery<Song> query =
                    em.createQuery(
                            "SELECT s FROM Song s " +
                            "WHERE LOWER(s.title) LIKE LOWER(:kw) " +
                            "OR LOWER(s.artist) LIKE LOWER(:kw) " +
                            "OR LOWER(s.genre) LIKE LOWER(:kw)",
                            Song.class);

            query.setParameter("kw", "%" + keyword + "%");

            return query.getResultList();

        } finally {
            em.close();
        }
    }
 // 1. Tìm theo ID (dùng cho chức năng Edit)
    public Song findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Song.class, id);
        } finally {
            em.close();
        }
    }

    // 2. Thêm mới
    public void create(Song song) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(song);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // 3. Cập nhật
    public void update(Song song) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(song);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // 4. Xóa
    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Song song = em.find(Song.class, id);
            if (song != null) {
                em.remove(song);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public static List<Song> findByUser(Long userId) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            return em.createQuery(
                    "SELECT s FROM Song s WHERE s.user.id = :uid",
                    Song.class)
                    .setParameter("uid", userId)
                    .getResultList();

        } finally {
            em.close();
        }
    }
    public static List<Song> findFavoriteSongs(Long userId) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Song s JOIN Favorite f ON s.id = f.song.id " +
                    "WHERE f.user.id = :uid",
                    Song.class)
                    .setParameter("uid", userId)
                    .getResultList();

        } finally {
            em.close();
        }
    }
    public List<Song> findLatest() {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Song s ORDER BY s.id DESC",
                    Song.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
    public List<Song> findTrending() {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Song s ORDER BY s.views DESC",
                    Song.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }
}