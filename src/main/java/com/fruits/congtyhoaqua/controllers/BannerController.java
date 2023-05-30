package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.FruitDTO;
import com.fruits.congtyhoaqua.models.Banner;
import com.fruits.congtyhoaqua.services.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/banners")
@CrossOrigin(origins = "*")
public class BannerController extends BaseController<Banner> {
    @Autowired
    private IBannerService bannerService;

    @GetMapping
    public ResponseEntity<?> getAllBanner() {
        return this.resListSuccess(bannerService.getAllBanner());
    }

    @PatchMapping("/image/{idBanner}")
    public ResponseEntity<?> editImageBanner(@PathVariable(name = "idBanner")Integer idBanner,
                                             @RequestParam MultipartFile image){
        return this.resSuccess(bannerService.editImageBanner(idBanner, image));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBanner(){
        return this.resSuccess(bannerService.createBanner());
    }

    @DeleteMapping("/{idBanner}")
    public ResponseEntity<?> deleteBanner(@PathVariable(name = "idBanner")Integer idBanner){
        return this.resSuccess(bannerService.deleteBanner(idBanner));
    }
}
