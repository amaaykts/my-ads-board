package oldService;

import model.User;

import java.util.List;

public interface UserService {
    User get(int id);

    List<User> list();

    int delete(int id);

    int update(User object);

    int save(User object);

}
