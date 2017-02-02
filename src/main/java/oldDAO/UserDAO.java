package oldDAO;

import model.User;

import java.util.List;

public interface UserDAO {
    User get(int id);

    List<User> list();

    int delete(int id);

    int update(User object);

    int add(User object);
}
