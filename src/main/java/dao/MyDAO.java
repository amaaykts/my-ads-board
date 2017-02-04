package dao;

import java.util.List;

public interface MyDAO {
    Object get(int id);

    Object get(String name);

    List<Object> list();

    int delete(int id);

    int update(Object object);

    int add(Object object);
}
