package org.alesapps.votingsystem.web.menu;

import org.alesapps.votingsystem.model.Menu;
import org.alesapps.votingsystem.service.MenuService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.alesapps.votingsystem.web.menu.MenuRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 08.05.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class MenuRestController {
    private static final Logger LOG = getLogger(MenuRestController.class);

    static final String REST_URL = "/api/v1/menus";

    private MenuService menuService;

    @Autowired
    public MenuRestController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll(@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll menus date {}", date);
        return menuService.getAllByDate(date);
    }
}
