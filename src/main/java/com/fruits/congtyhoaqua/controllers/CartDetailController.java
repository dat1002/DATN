package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.CartDetail;
import com.fruits.congtyhoaqua.services.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RestController
@RequestMapping("/cart-detail")
@CrossOrigin(origins = "*")
public class CartDetailController extends BaseController<CartDetail> {
    @Autowired
    private ICartDetailService iCartDetailService;

    @PostMapping("/create/{idCart}")
    public ResponseEntity<?> createCartDetail(@PathVariable(name = "idCart") Integer idCart,
                                              @RequestBody CartDetailDTO cartDetailDTO
                                              ){
        return this.resSuccess(iCartDetailService.createCartDetail(idCart, cartDetailDTO));
    }

    @DeleteMapping("delete/{idCart}")
    public ResponseEntity<?> deleteByIdCart(@PathVariable(name = "idCart") Integer idCart){
        return this.resSetSuccess(iCartDetailService.deleteByIdCart(idCart));
    }

    @DeleteMapping("deleteByIdCart/{idCart}/{idFruit}")
    public ResponseEntity<?> deleteByIdCart(@PathVariable(name = "idCart") Integer idCart,
                                            @PathVariable(name = "idFruit") Integer idFruit){
        return this.resSuccess(iCartDetailService.deleteByIdCartDetail(idCart, idFruit));
    }

    @GetMapping("getByIdCart/{idCart}")
    public ResponseEntity<?> getAllCartDetailByIdCart(@PathVariable(name = "idCart") Integer idCart){
        return this.resSetSuccess(iCartDetailService.getAllCartDetailByIdCart(idCart));
    }

    @GetMapping("getAll")
    public ResponseEntity<?> getAll(){
        return this.resSetSuccess(iCartDetailService.getAll());
    }
}
