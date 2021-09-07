package com.ninyo.pacing.application.service;

import com.ninyo.pacing.model.PacingResponse;

public interface PacingService {

    PacingResponse addImpression(String adId);

    PacingResponse getImpressions(String adId);
}
