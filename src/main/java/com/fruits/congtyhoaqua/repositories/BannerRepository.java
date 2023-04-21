package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Banner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
   Banner findBannerById(Integer idBanner);
}
