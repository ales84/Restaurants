package org.alesapps.votingsystem.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@NamedQueries({
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant r WHERE r.id=:id"),
        @NamedQuery(name = Restaurant.DELETE_ALL, query = "DELETE FROM Restaurant r"),
        @NamedQuery(name = Restaurant.GET_ALL, query = "SELECT r FROM Restaurant r")
})
@Entity
@Table(name = "restaurants", uniqueConstraints = {@UniqueConstraint(name = "restaurants_name_idx", columnNames = "name")})
public class Restaurant extends BaseEntity {

    public static final String DELETE = "Restaurant.delete";
    public static final String DELETE_ALL = "Restaurant.deleteAll";
    public static final String GET_ALL = "Restaurant.getAll";

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + getId() +
                ", name=" + name +
                '}';
    }
}
