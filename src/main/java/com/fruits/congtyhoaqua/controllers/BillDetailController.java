package com.fruits.congtyhoaqua.controllers;
import com.fruits.congtyhoaqua.bases.BaseController;

import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.services.IBillDetailService;
import com.fruits.congtyhoaqua.services.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill-detail")
@CrossOrigin(origins = "*")
public class BillDetailController extends BaseController<BillDetail>{
    @Autowired
    private IBillDetailService iBillDetailService;

    @GetMapping("findAllByIdBill/{idBill}")
    public ResponseEntity<?> findAllByIdBill(@PathVariable(name = "idBill") Integer idBill){
        return this.resListSuccess(iBillDetailService.findAllByIdBill(idBill));
    }
}