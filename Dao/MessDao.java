package Dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import Model.Mess;
import utils.JpaUtil;

public class MessDao {

    public List<MessDao> findAll() {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            TypedQuery<MessDao> query =
                em.createQuery(
                    "SELECT n FROM Notification n ORDER BY n.createdAt DESC",
                    MessDao.class);

            return query.getResultList();

        } finally {
            em.close();
        }
    }

    public void create(MessDao notification) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            em.getTransaction().begin();

            em.persist(notification);

            em.getTransaction().commit();

        } catch(Exception e) {

            em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();
        }
    }
}