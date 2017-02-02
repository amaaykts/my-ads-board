package oldDAO;

import model.Category;

import java.util.List;

public interface CategoryDAO {
    Category get(int id);

    List<Category> list();

    int delete(int id);

    int update(Category object);

    int add(Category object);
}
