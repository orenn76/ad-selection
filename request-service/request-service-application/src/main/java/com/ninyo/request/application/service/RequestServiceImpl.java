package com.ninyo.request.application.service;

import com.ninyo.pacing.client.PacingServiceClient;
import com.ninyo.request.application.exception.RequestException;
import com.ninyo.request.application.model.Ad;
import com.ninyo.request.application.repository.DeliveryPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    private DeliveryPlanRepository deliveryPlanRepository;

    private PacingServiceClient pacingServiceClient;

    public RequestServiceImpl(DeliveryPlanRepository deliveryPlanRepository, PacingServiceClient pacingServiceClient) {
        this.deliveryPlanRepository = deliveryPlanRepository;
        this.pacingServiceClient = pacingServiceClient;
    }

    @Override
    public String selectAd(List<String> adIds) {
        List<Ad> ads = deliveryPlanRepository.getAds(adIds);

        if (!CollectionUtils.isEmpty(ads)) {
            ads = ads.stream().filter(ad -> ad.getQuota() - pacingServiceClient.getImpressions(ad.getId()).getImpressions() > 0).collect(Collectors.toList());

            if (ads.size() > 0) {
                if (ads.size() == 1) {
                    return ads.get(0).getId();
                }
                ads.sort(Comparator.comparing(Ad::getWeight).reversed());
                return ads.get(0).getId();
            }
        }

        log.error("Could not select a suitable ad");
        throw new RequestException("Could not select a suitable ad");
    }
}
