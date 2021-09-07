package com.ninyo.request.application.controller;

import com.ninyo.request.application.model.RequestResponse;
import com.ninyo.request.application.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/selectAd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public RequestResponse selectAd(@RequestBody List<String> adIds) {
        String adId = requestService.selectAd(adIds);
        return RequestResponse.builder()
                .adId(adId)
                .build();
    }
}