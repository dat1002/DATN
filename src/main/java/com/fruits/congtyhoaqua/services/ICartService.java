package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.Bill;
import com.fruits.congtyhoaqua.models.Cart;

import java.util.Set;

public interface ICartService {
    Cart createCart(Integer idUser);
    Cart getCartByIdUser(Integer idUser);
    Cart getCartById(Integer idCart);
    Cart editCart(Integer idCart, Set<CartDetailDTO> cartDetailDTOS);
}
