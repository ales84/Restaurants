package org.alesapps.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@NamedQueries({
        @NamedQuery(name = Menu.DELETE, query = "DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId"),
        @NamedQuery(name = Menu.DELETE_ALL, query = "DELETE FROM Menu m"),
        @NamedQuery(name = Menu.GET_ALL, query = "SELECT m FROM Menu m"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE, query = "SELECT m FROM Menu m WHERE m.date=:date"),
        @NamedQuery(name = Menu.GET_ALL_BY_DATE_AND_RESTAURANT, query = "SELECT m FROM Menu m WHERE m.date=:date" +
                " AND m.restaurant.id=:restaurantId")
})
@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(name = "menus_restaurant_date_idx", columnNames = {"restaurant_id", "date"})})
public class Menu extends BaseEntity {

    public static final String DELETE = "Menu.delete";
    public static final String DELETE_ALL = "Menu.deleteAll";
    public static final String GET_ALL = "Menu.getAll";
    public static final String GET_ALL_BY_DATE = "Menu.getAllByDate";
    public static final String GET_ALL_BY_DATE_AND_RESTAURANT = "Menu.getAllByDateAndRestaurant";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

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
                "id=" + getId() +
                ", restaurant=" + restaurant +
                ", date=" + date +
                ", dishes=" + dishes +
                '}';
    }
}
