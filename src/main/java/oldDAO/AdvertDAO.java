package oldDAO;

import model.Advert;

import java.util.List;

public interface AdvertDAO {
    Advert get(int id);

    List<Advert> list();

    int delete(int id);

    int update(Advert object);

    int add(Advert object);
}
