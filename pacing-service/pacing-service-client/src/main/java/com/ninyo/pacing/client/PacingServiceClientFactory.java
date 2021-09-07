package com.ninyo.pacing.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninyo.client.FeignClientFactory;
import com.ninyo.pacing.client.exception.ClientErrorDecoder;
import feign.jackson.JacksonDecoder;

public class PacingServiceClientFactory {

    public PacingServiceClient createClient(String serviceUrl, ObjectMapper objectMapper) {
        return FeignClientFactory.createClient(PacingServiceClient.class, serviceUrl, objectMapper, new ClientErrorDecoder(new JacksonDecoder(objectMapper)));
    }
}
