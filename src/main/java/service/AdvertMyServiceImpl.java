package service;

import dao.MyDAO;

import java.util.List;

public class AdvertMyServiceImpl implements MyService {

   private MyDAO dao;

    public Object get(int id) {
        return dao.get(id);
    }

    public List<Object> list() {
        return dao.list();
    }

    public int delete(int id) {
        return dao.delete(id);
    }

    public Object update(Object object) {
        return dao.update(object);
    }
}
