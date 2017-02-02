package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@EnableTransactionManagement
@Transactional
public class UserDAOImpl implements MyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public User get(int id) {
        Session session = sessionFactory.openSession();
        User advert = (User) session.get(User.class, id);
        session.close();
        return advert;
    }

    public List<Object> list() {
        Session session = sessionFactory.openSession();
        List<Object> list = session.getNamedQuery(User.GET_USER_LIST).list();
        session.close();
        return list;
    }

    @Transactional
    public int delete(int id) {
        Session session = sessionFactory.openSession();
        User category = (User) session.get(User.class, id);
        session.delete(category);
        Serializable identifier = session.getIdentifier(category);
        session.flush();
        session.close();
        return (Integer) identifier;
    }

    @Transactional
    public int update(Object object) {
        Session session = sessionFactory.openSession();
        session.update(object);
        Serializable id = session.getIdentifier(object);
        session.flush();
        session.close();
        return (Integer) id;
    }

    @Transactional
    public int add(Object object) {
        Session session = sessionFactory.openSession();
        Serializable serializable = session.save(object);
        session.close();
        return (Integer) serializable;
    }
}
