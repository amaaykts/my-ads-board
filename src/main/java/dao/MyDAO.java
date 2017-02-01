package dao;

import java.util.List;

public interface MyDAO {
    Object get(int id);

    List<Object> list();

    int delete(int id);

    Object update(Object object);
}
