package com.ninyo.request.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ninyo.pacing.client.PacingServiceClient;
import com.ninyo.pacing.client.PacingServiceClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class FeignConfig {

    @Bean
    public ObjectMapper objectMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return mapper;
    }

    @Bean
    public PacingServiceClient pacingServiceClient(@Value("${pacingServiceClientUrl:http://localhost:8090/pacing-service}") String pacingServiceClientUrl) {
        try {
            log.info("pacingServiceClientUrl host: {}", pacingServiceClientUrl);
            return new PacingServiceClientFactory().createClient(pacingServiceClientUrl, objectMapper());
        } catch (Throwable ex) {
            throw ex;
        }
    }

}
