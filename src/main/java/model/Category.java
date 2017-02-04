package model;

import javax.persistence.*;

@Entity
@Table(name = "category")
@NamedQueries(
        {
                @NamedQuery(name = Category.GET_CATEGORY, query = "select c from Category c where c.id=:id"),
                @NamedQuery(name = Category.GET_CATEGORY_NAME, query = "select c from Category c where c.name=:name"),
                @NamedQuery(name = Category.GET_CATEGORY_LIST, query = "select c from Category c")
        })
public class Category {
    public static final String GET_CATEGORY = "Category.GET_CATEGORY";
    public static final String GET_CATEGORY_NAME = "Category.GET_CATEGORY_NAME";
    public static final String GET_CATEGORY_LIST = "Category.GET_CATEGORY_LIST";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
