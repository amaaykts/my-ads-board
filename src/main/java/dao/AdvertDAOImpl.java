package dao;

import oldDAO.AdvertDAO;
import model.Advert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ARTEM on 02.02.2017.
 */
@EnableTransactionManagement
@Transactional
public class AdvertDAOImpl implements AdvertDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Advert get(int id) {
        Session session = sessionFactory.openSession();
        Advert advert = (Advert) session.get(Advert.class, id);
        session.close();
        return advert;
    }

    public List<Advert> list() {
        Session session = sessionFactory.openSession();
        List<Advert> list = session.getNamedQuery(Advert.GET_ADVERT_LIST).list();
        session.close();
        return list;
    }

    @Transactional
    public int delete(int id) {
        Session session = sessionFactory.openSession();
        Advert advert = (Advert) session.get(Advert.class, id);
        session.delete(advert);
        Serializable identifier = session.getIdentifier(advert);
        session.flush();
        session.close();
        return (Integer) identifier;
    }

    @Transactional
    public int update(Advert object) {
        Session session = sessionFactory.openSession();
        session.update(object);
        Serializable id = session.getIdentifier(object);
        session.flush();
        session.close();
        return (Integer) id;
    }

    @Transactional
    public int add(Advert object) {
        Session session = sessionFactory.openSession();
        Serializable serializable = session.save(object);
        session.close();
        return (Integer) serializable;
    }
}
