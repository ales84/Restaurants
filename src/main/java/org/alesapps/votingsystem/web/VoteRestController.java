package org.alesapps.votingsystem.web;

import org.alesapps.votingsystem.model.Vote;
import org.alesapps.votingsystem.service.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static org.alesapps.votingsystem.web.VoteRestController.REST_URL;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Anatoliy Kozhayev on 10.06.2017.
 */
@RestController
@RequestMapping(REST_URL)
public class VoteRestController {
    private static final Logger LOG = getLogger(VoteRestController.class);

    static final String REST_URL = "/api/v1/admin/votes";

    private VoteService voteService;

    @Autowired
    public VoteRestController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vote> getAll(@RequestParam(value = "date")
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LOG.info("getAll votes date {}", date);
        return voteService.getAllByDate(date);
    }
}
