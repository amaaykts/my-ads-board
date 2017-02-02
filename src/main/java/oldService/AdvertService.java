package oldService;

import model.Advert;

import java.util.List;

public interface AdvertService {
    Advert get(int id);

    List<Advert> list();

    int delete(int id);

    int update(Advert object);

    int save(Advert object);

}
