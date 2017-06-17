package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.BaseEntity;
import org.alesapps.votingsystem.model.Dish;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class DishTestData {

    public static final int DISH1_ID = BaseEntity.START_SEQ + 12;

    public static final Dish DISH1 = new Dish(DISH1_ID, "Шашлык", new BigDecimal(500));
    public static final Dish DISH2 = new Dish(DISH1_ID + 1, "Салат", new BigDecimal(200));
    public static final Dish DISH3 = new Dish(DISH1_ID + 2, "Утка", new BigDecimal(700));
    public static final Dish DISH4 = new Dish(DISH1_ID + 3, "Окрошка", new BigDecimal(200));
    public static final Dish DISH5 = new Dish(DISH1_ID + 4, "Хлеб", new BigDecimal(50));
    public static final Dish DISH6 = new Dish(DISH1_ID + 5, "Салат", new BigDecimal(100));
    public static final Dish DISH7 = new Dish(DISH1_ID + 6, "Пицца", new BigDecimal(300));
    public static final Dish DISH8 = new Dish(DISH1_ID + 7, "Борщ", new BigDecimal(200));
    public static final Dish DISH9 = new Dish(DISH1_ID + 8, "Суши", new BigDecimal(220));

    public static final Set<Dish> DISHES_MENU1 = new HashSet<>(Arrays.asList(DISH1, DISH2));
    public static final Set<Dish> DISHES_MENU2 = new HashSet<>(Arrays.asList(DISH3));
    public static final Set<Dish> DISHES_MENU3 = new HashSet<>(Arrays.asList(DISH4, DISH5, DISH6));
    public static final Set<Dish> DISHES_MENU4 = new HashSet<>(Arrays.asList(DISH7));
    public static final Set<Dish> DISHES_MENU5 = new HashSet<>(Arrays.asList(DISH8));
    public static final Set<Dish> DISHES_MENU6 = new HashSet<>(Arrays.asList(DISH9));
}
