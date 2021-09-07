package com.ninyo.request.application.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninyo.request.application.model.Ad;
import com.ninyo.request.application.model.AdList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class DeliveryPlanRepository {

    private AdList adsList;

    @Value("${fileName}")
    private String fileName;

    @PostConstruct
    public void init() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        adsList = objectMapper.readValue(
                getClass().getClassLoader().getResourceAsStream(fileName),
                new TypeReference<AdList>() {
                });
    }

    public List<Ad> getAds(List<String> adIds) {
        return adsList.getAds().stream().filter(ad -> adIds.contains(ad.getId())).collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * *") // everyday at midnight
    public void refreshDeliveryPlan() {
        try {
            init();
        } catch (IOException e) {
            log.error("Could not refresh delivery plan", e);
        }
    }

}
