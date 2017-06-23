package org.alesapps.votingsystem.web.restaurant;

import org.alesapps.votingsystem.AuthorizedUser;
import org.alesapps.votingsystem.model.Restaurant;
import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.service.RestaurantService;
import org.alesapps.votingsystem.service.VoteService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.alesapps.votingsystem.web.restaurant.RestaurantVoteRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class RestaurantVoteRestController {
    private static final Logger LOG = getLogger(RestaurantVoteRestController.class);

    static final String REST_URL = "/api/v1/restaurants";

    private RestaurantService restaurantService;
    private VoteService voteService;

    @Autowired
    public RestaurantVoteRestController(RestaurantService restaurantService, VoteService voteService) {
        this.restaurantService = restaurantService;
        this.voteService = voteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAllRestaurants() {
        LOG.info("getAll restaurants");
        return restaurantService.getAll();
    }

    @PostMapping(value = "/{restaurantId}/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote create(@PathVariable Integer restaurantId,
                       @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        Vote vote = new Vote(null, null, null, LocalDate.now());
        LOG.info("create {}", vote);
        return voteService.create(vote, authorizedUser.getId(), restaurantId);
    }
}
