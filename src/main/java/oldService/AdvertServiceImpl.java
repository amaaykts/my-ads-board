package oldService;

import model.Advert;
import oldDAO.AdvertDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AdvertServiceImpl implements AdvertService {

    @Autowired
    @Qualifier(value = "advertDAO")
    private AdvertDAO dao;

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

    public int save(Advert object) {
        return dao.add(object);
    }
}
