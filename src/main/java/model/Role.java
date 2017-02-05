package model;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NamedQueries(
        {
                @NamedQuery(name = Role.GET_ROLE, query = "select r from Role r where r.id=:id"),
                @NamedQuery(name = Role.GET_ROLE_LIST, query = "select r from Role r"),
                @NamedQuery(name = Role.GET_ROLE_NAME, query = "select r from Role r where r.name = :name")
        })
public class Role {
    public static final String GET_ROLE = "Role.GET_ROLE";
    public static final String GET_ROLE_LIST = "Role.GET_ROLE_LIST";
    public static final String GET_ROLE_NAME = "Role.GET_ROLE_NAME";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
