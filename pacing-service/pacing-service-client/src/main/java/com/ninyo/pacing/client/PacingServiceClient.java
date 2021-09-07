package com.ninyo.pacing.client;

import com.ninyo.pacing.model.PacingResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface PacingServiceClient {

    @RequestLine("POST ads/{adId}/impressions")
    void addImpression(@Param("adId") String adId);

    @RequestLine("GET ads/{adId}/impressions")
    PacingResponse getImpressions(@Param("adId") String adId);

}
