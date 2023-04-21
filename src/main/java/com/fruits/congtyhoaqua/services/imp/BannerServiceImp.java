package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Banner;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.repositories.BannerRepository;
import com.fruits.congtyhoaqua.services.IBannerService;
import com.fruits.congtyhoaqua.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class BannerServiceImp implements IBannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private UploadFile uploadFile;


    @Override
    public Set<Banner> getAllBanner(){
        Set<Banner> banners = new HashSet<>(bannerRepository.findAll());
        if(banners.isEmpty()){
            throw new NotFoundException("No banner");
        }
        return banners;
    }

    @Override
    public Banner createBanner() {
//        Optional<Banner> banner = bannerRepository.findById(idBanner);
//        if (banner.isEmpty()) {
//            throw new NotFoundException("No Banner");
//        }
        Banner banner = new Banner();
        Banner banner1 = bannerRepository.save(banner);
        return banner1;
    }

    @Override
    public Banner editImageBanner(Integer idBanner, MultipartFile image) {
        Optional<Banner> banner = bannerRepository.findById(idBanner);
        if (banner.isEmpty()){
            throw new NotFoundException("No Banner");
        }
        banner.get().setImage(uploadFile.getUrlFromFile(image));
        return bannerRepository.save(banner.get());
    }

    @Override
    public Banner deleteBanner(Integer idBanner) {
        Optional<Banner> banner = bannerRepository.findById(idBanner);
        if (banner.isEmpty()){
            throw new NotFoundException("No Banner");
        }
        bannerRepository.delete(banner.get());
        return banner.get();
    }
}
