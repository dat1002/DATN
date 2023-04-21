package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.services.ICartDetailService;
import com.fruits.congtyhoaqua.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController extends BaseController<Cart> {
    @Autowired
    private ICartService iCartService;

    @PatchMapping("/edit/{idCart}")
    public ResponseEntity<?> createCartDetail(@PathVariable(name = "idCart") Integer idCart,
                                              @RequestBody Set<CartDetailDTO> cartDetailDTOS){
        return this.resSuccess(iCartService.editCart(idCart, cartDetailDTOS));
    }

    @PostMapping("/create/{idUser}")
    public ResponseEntity<?> createCart(@PathVariable(name = "idUser") Integer idUser
                                             ){
        return this.resSuccess(iCartService.createCart(idUser));
    }

    @GetMapping("/getByIdUser/{idUser}")
    public ResponseEntity<?> getCartByIdUser(@PathVariable(name = "idUser") Integer idUser
    ){
        return this.resSuccess(iCartService.getCartByIdUser(idUser));
    }

}
