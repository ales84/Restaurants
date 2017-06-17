package org.alesapps.votingsystem;

import org.alesapps.votingsystem.model.BaseEntity;
import org.alesapps.votingsystem.model.Menu;

import java.time.LocalDate;

import static org.alesapps.votingsystem.DishTestData.*;
import static org.alesapps.votingsystem.RestaurantTestData.*;

/**
 * Created by Anatoliy Kozhayev on 16.06.2017.
 */
public class MenuTestData {

    public static final int MENU1_ID = BaseEntity.START_SEQ + 6;

    public static final Menu MENU1 = new Menu(MENU1_ID, RESTAURANT1, LocalDate.of(2017, 6, 4), DISHES_MENU1);
    public static final Menu MENU2 = new Menu(MENU1_ID + 1, RESTAURANT2, LocalDate.of(2017, 6, 4), DISHES_MENU2);
    public static final Menu MENU3 = new Menu(MENU1_ID + 2, RESTAURANT3, LocalDate.of(2017, 6, 4), DISHES_MENU3);
    public static final Menu MENU4 = new Menu(MENU1_ID + 3, RESTAURANT1, LocalDate.of(2017, 6, 8), DISHES_MENU4);
    public static final Menu MENU5 = new Menu(MENU1_ID + 4, RESTAURANT2, LocalDate.of(2017, 6, 8), DISHES_MENU5);
    public static final Menu MENU6 = new Menu(MENU1_ID + 5, RESTAURANT3, LocalDate.of(2017, 6, 8), DISHES_MENU6);
}
