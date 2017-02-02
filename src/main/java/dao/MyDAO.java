package dao;

import model.Role;

import java.util.List;

public interface MyDAO {
    Object get(int id);

    List<Object> list();

    int delete(int id);

    int update(Object object);

    int add(Object object);
}
