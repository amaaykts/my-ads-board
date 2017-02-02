package oldDAO;

import model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@EnableTransactionManagement
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Role get(int id) {
        Session session = sessionFactory.openSession();
        Role advert = (Role) session.get(Role.class, id);
        session.close();
        return advert;
    }

    public List<Role> list() {
        Session session = sessionFactory.openSession();
        List<Role> list = session.getNamedQuery(Role.GET_ROLE_LIST).list();
        session.close();
        return list;
    }

    @Transactional
    public int delete(int id) {
        Session session = sessionFactory.openSession();
        Role category = (Role) session.get(Role.class, id);
        session.delete(category);
        Serializable identifier = session.getIdentifier(category);
        session.flush();
        session.close();
        return (Integer) identifier;
    }

    @Transactional
    public int update(Role object) {
        Session session = sessionFactory.openSession();
        session.update(object);
        Serializable id = session.getIdentifier(object);
        session.flush();
        session.close();
        return (Integer) id;
    }

    @Transactional
    public int add(Role object) {
        Session session = sessionFactory.openSession();
        Serializable serializable = session.save(object);
        session.close();
        return (Integer) serializable;
    }
}
