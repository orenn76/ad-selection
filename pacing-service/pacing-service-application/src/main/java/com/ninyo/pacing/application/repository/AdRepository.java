package com.ninyo.pacing.application.repository;

import com.ninyo.pacing.application.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

    @Query("select a from Ad a where a.addId = ?1")
    Ad findByAdId(String addId);
}
