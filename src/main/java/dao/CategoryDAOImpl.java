package dao;

import model.Advert;
import model.Category;
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
public class CategoryDAOImpl implements MyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Category get(int id) {
        Session session = sessionFactory.openSession();
        Category advert = (Category) session.get(Category.class, id);
        session.close();
        return advert;
    }

    @Override
    public Object get(String name) {
        Session session = sessionFactory.openSession();
        Category advert = (Category) session.getNamedQuery(Category.GET_CATEGORY_NAME).setParameter("name", name).uniqueResult();
        session.close();
        return advert;
    }

    public List<Object> list() {
        Session session = sessionFactory.openSession();
        List<Object> list = session.getNamedQuery(Category.GET_CATEGORY_LIST).list();
        session.close();
        return list;
    }

    @Transactional
    public int delete(int id) {
        Session session = sessionFactory.openSession();
        Category category = (Category) session.get(Category.class, id);
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
