package org.alesapps.votingsystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
@NamedQueries({
        @NamedQuery(name = Vote.DELETE, query = "DELETE FROM Vote v WHERE v.id=:id"),
        @NamedQuery(name = Vote.DELETE_ALL, query = "DELETE FROM Vote v"),
        @NamedQuery(name = Vote.GET_ALL, query = "SELECT v FROM Vote v"),
        @NamedQuery(name = Vote.GET_ALL_BY_DATE, query = "SELECT v FROM Vote v WHERE v.date=:date")
})
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(name = "votes_user_date_idx", columnNames = {"user_id", "date"})})
public class Vote extends BaseEntity {

    public static final String DELETE = "Vote.delete";
    public static final String DELETE_ALL = "Vote.deleteAll";
    public static final String GET_ALL = "Vote.getAll";
    public static final String GET_ALL_BY_DATE = "Vote.getAllByDate";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(name = "date", columnDefinition = "timestamp default now()")
    private LocalDate date;

    public Vote() {
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate date) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                ", user=" + user +
                ", restaurant=" + restaurant +
                ", date=" + date +
                '}';
    }
}
