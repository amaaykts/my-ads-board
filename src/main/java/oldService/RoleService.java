package oldService;

import model.Role;

import java.util.List;

public interface RoleService {
    Role get(int id);

    List<Role> list();

    int delete(int id);

    int update(Role object);

    int save(Role object);

}
