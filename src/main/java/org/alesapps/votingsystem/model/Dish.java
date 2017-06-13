package org.alesapps.votingsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Anatoliy Kozhayev on 17.04.2017.
 */
@NamedQueries({
        @NamedQuery(name = Dish.DELETE, query = "DELETE FROM Dish d WHERE d.id=:id AND d.menu.id=:menuId"),
        @NamedQuery(name = Dish.DELETE_ALL_BY_MENU_ID, query = "DELETE FROM Dish d WHERE d.menu.id=:menuId"),
        @NamedQuery(name = Dish.GET_ALL, query = "SELECT d FROM Dish d"),
        @NamedQuery(name = Dish.GET_ALL_BY_MENU_ID, query = "SELECT d FROM Dish d WHERE d.menu.id=:menuId")
})
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity {

    public static final String DELETE = "Dish.delete";
    public static final String DELETE_ALL_BY_MENU_ID = "Dish.deleteAllByMenuId";
    public static final String GET_ALL = "Dish.getAll";
    public static final String GET_ALL_BY_MENU_ID = "Dish.getAllByMenuId";

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public Dish() {
    }

    public Dish(Integer id, String name, BigDecimal price, Menu menu) {
        super(id);
        this.name = name;
        this.price = price;
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + getId() +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
