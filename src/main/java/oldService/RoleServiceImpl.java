package oldService;

import model.Role;
import oldDAO.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    @Autowired
    @Qualifier(value = "roleDAO")
    private RoleDAO dao;

    public Role get(int id) {
        return dao.get(id);
    }

    public List<Role> list() {
        return dao.list();
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public int update(Role object) {
        return dao.update(object);
    }

    public int save(Role object) {
        return dao.add(object);
    }
}
