package oldDAO;

import model.Role;

import java.util.List;

public interface RoleDAO {
    Role get(int id);

    List<Role> list();

    int delete(int id);

    int update(Role object);

    int add(Role object);
}
