package com.fruits.congtyhoaqua.repositories;

import com.fruits.congtyhoaqua.models.Banner;
import com.fruits.congtyhoaqua.models.Knowledge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {
   @Query(nativeQuery = true, value = "select * from banners order by id ")
   List<Banner> selectAll();
   Banner findBannerById(Integer idBanner);
}
