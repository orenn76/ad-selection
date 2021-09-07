package com.ninyo.pacing.application.service;

import com.ninyo.pacing.application.model.Ad;
import com.ninyo.pacing.model.PacingResponse;
import com.ninyo.pacing.application.repository.AdRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PacingServiceImpl implements PacingService {

    private AdRepository adRepository;

    public PacingServiceImpl(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    @CachePut(cacheNames = "ads")
    public PacingResponse addImpression(String adId) {
        Ad ad = adRepository.findByAdId(adId);
        if (ad == null) {
            ad = Ad.builder().addId(adId).build();
        }
        ad.setImpressions(ad.getImpressions() + 1);
        adRepository.save(ad);
        return PacingResponse.builder().impressions(ad.getImpressions()).build();
    }

    @Override
    @Cacheable(cacheNames = "ads")
    public PacingResponse getImpressions(String adId) {
        Ad ad = adRepository.findByAdId(adId);
        if (ad == null) {
            log.warn("Ad with adId: {}, was not found", adId);
            return PacingResponse.builder().impressions(0).build();
        }
        return PacingResponse.builder().impressions(ad.getImpressions()).build();
    }

    @Scheduled(cron = "0 0 0 * * *") // everyday at midnight
    @CacheEvict(cacheNames = "ads")
    public void deleteDBAndCache() {
        adRepository.deleteAll();
    }

}
