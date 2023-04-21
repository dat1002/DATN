package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.models.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface IBannerService {
    Set<Banner> getAllBanner();
    Banner createBanner();
    Banner editImageBanner(Integer idBanner, MultipartFile image);
    Banner deleteBanner(Integer idBanner);
}
