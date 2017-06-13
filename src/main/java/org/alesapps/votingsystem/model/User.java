package org.alesapps.votingsystem.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@NamedQueries({
        @NamedQuery(name = User.DELETE, query = "DELETE FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.GET_BY_NAME, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.name=:name"),
        @NamedQuery(name = User.GET_ALL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(name = "user_name_idx", columnNames = "name")})
public class User extends BaseEntity {

    public static final String DELETE = "User.delete";
    public static final String GET_BY_NAME = "User.getByName";
    public static final String GET_ALL = "User.getAll";

    @NotBlank
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @NotBlank
    @Length(min = 5)
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    private Set<Role> roles;

    public User() {
    }

    public User(Integer id, String name, String password, Role role, Role... roles) {
        this(id, name, password, EnumSet.of(role, roles));
    }

    public User(Integer id, String name, String password, Set<Role> roles) {
        super(id);
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name=" + name +
                ", roles=" + roles +
                '}';
    }
}
