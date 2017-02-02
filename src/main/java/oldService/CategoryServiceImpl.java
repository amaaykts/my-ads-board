package oldService;

import model.Advert;
import oldDAO.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

/**
 * Created by ARTEM on 02.02.2017.
 */
public class CategoryServiceImpl implements CategoryDAO {
    @Autowired
    @Qualifier(value = "categoryDAO")
    private CategoryDAO dao;

    public Advert get(int id) {
        return dao.get(id);
    }

    public List<Advert> list() {
        return dao.list();
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public int update(Advert object) {
        return dao.update(object);
    }

    public int add(Advert object) {
        return dao.add(object);
    }
}
