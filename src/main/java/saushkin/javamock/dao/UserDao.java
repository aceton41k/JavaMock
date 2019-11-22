package saushkin.javamock.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import saushkin.javamock.model.User;
import saushkin.javamock.utils.HibernateSessionFactory;

import java.util.List;

public class UserDao {

    public User getUser(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(User.class, id);
    }

    public void addUser(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        List<User> users = (List<User>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From User").list();
        return users;
    }
}
