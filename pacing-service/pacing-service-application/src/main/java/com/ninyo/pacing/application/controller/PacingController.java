package com.ninyo.pacing.application.controller;

import com.ninyo.pacing.model.PacingResponse;
import com.ninyo.pacing.application.service.PacingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class PacingController {

    @Autowired
    private PacingService pacingService;

    @RequestMapping(value = "ads/{adId}/impressions", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addImpression(@PathVariable("adId") String adId) {
        pacingService.addImpression(adId);
    }

    @RequestMapping(value = "ads/{adId}/impressions", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PacingResponse getImpressions(@PathVariable("adId") String adId) {
        return pacingService.getImpressions(adId);
    }

}