package org.alesapps.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.alesapps.votingsystem.json.View;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id"),
        @NamedQuery(name = Menu.ALL, query = "SELECT m FROM Menu m")
})
@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(name = "menus_restaurant_date_idx", columnNames = {"restaurant_id", "date"})})
public class Menu extends BaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String ALL = "Menu.getAll";

    @JsonView(View.Summary.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @JsonView(View.Summary.class)
    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    private Set<Dish> dishes;

    public Menu() {
    }

    public Menu(Integer id, Restaurant restaurant, LocalDate date, Set<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + getId() +
                "restaurant=" + restaurant.getName() +
                ", date=" + date +
                ", dishes=" + dishes.size() +
                '}';
    }
}
