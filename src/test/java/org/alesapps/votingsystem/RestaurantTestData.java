package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.BaseEntity;
import org.alesapps.votingsystem.model.Restaurant;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class RestaurantTestData {

    public static final int RESTAURANT1_ID = BaseEntity.START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Бочонок");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Форпост");
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT1_ID + 2, "Рубин");

    public static final List<Restaurant> RESTAURANTS = Arrays.asList(RESTAURANT1, RESTAURANT2, RESTAURANT3);
}
