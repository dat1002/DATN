package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.CategoryDTO;
import com.fruits.congtyhoaqua.dtos.ManufactureDTO;
import com.fruits.congtyhoaqua.models.Manufacture;
import com.fruits.congtyhoaqua.services.IManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manufacture")
@CrossOrigin(origins = "*")
public class ManufactureController extends BaseController<Manufacture> {
    @Autowired
    private IManufactureService manufactureService;

    @PostMapping("/create")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createManufacture(@RequestBody ManufactureDTO manufactureDTO){
        return this.resSuccess(manufactureService.createManufacture(manufactureDTO));
    }

    @PatchMapping("/edit-manufacture/{idManufacture}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editManufacture(@PathVariable Integer idManufacture, @RequestBody ManufactureDTO manufactureDTO){
        return  this.resSuccess(manufactureService.editManufacture(idManufacture, manufactureDTO));
    }

    @DeleteMapping("delete-manufacture/{idManufacture}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteManufacture(@PathVariable Integer idManufacture){
        return this.resSuccess(manufactureService.deleteManufacture(idManufacture));
    }

    @GetMapping("/get-all-manufacture")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllManufacture(){
        return  this.resSetSuccess(manufactureService.getAllManufacture());
    }

    @GetMapping("/{idManufacture}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getManufacture(@PathVariable Integer idManufacture){
        return this.resSuccess(manufactureService.getManufacture(idManufacture));
    }

    @GetMapping("/find-by-name-manufacture")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getManufactureByName(@RequestParam String name){
        return this.resSetSuccess(manufactureService.getManufactureByName(name));
    }
}
