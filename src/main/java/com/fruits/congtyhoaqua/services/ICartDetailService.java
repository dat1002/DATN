package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.models.CartDetail;

import java.util.Set;

public interface ICartDetailService {
    CartDetail createCartDetail(Integer idCart,CartDetailDTO cartDetailDTO);
    Set<CartDetail> getAllCartDetailByIdCart(Integer idCart);
    CartDetail deleteByIdCartDetail(Integer idCart, Integer idFruit);
    Set<CartDetail> deleteByIdCart(Integer idCart);
    Set<CartDetail> getAll();

}
