package service;

import java.util.List;

public interface MyService {
    Object get(int id);

    List<Object> list();

    int delete(int id);

    Object update(Object object);
}
