package service;

import dao.MyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class AdvertServiceImpl implements MyService {

    @Autowired
    @Qualifier(value = "advertDAO")
    private MyDAO dao;

    @Override
    public Object get(String name) {
        return dao.get(name);
    }

    public Object get(int id) {
        return dao.get(id);
    }

    public List<Object> list() {
        return dao.list();
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public int update(Object object) {
        return dao.update(object);
    }

    public int add(Object object) {
        return dao.add(object);
    }
}
