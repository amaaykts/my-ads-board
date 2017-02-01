package model;

import javax.persistence.*;

@Entity
@Table(name = "role")
@NamedQuery(name = Role.GET_ROLE, query = "select r from Role r where r.name = :name")

public class Role {
    public static final String GET_ROLE = "Role.getRole";

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
