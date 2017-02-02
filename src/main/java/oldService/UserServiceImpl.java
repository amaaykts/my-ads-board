package oldService;

import model.User;
import oldDAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier(value = "userDAO")
    private UserDAO dao;

    public User get(int id) {
        return dao.get(id);
    }

    public List<User> list() {
        return dao.list();
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public int update(User object) {
        return dao.update(object);
    }

    public int save(User object) {
        return dao.add(object);
    }
}
