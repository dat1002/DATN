package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.BillDetailDTO;
import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.models.BillDetail;
import com.fruits.congtyhoaqua.models.CartDetail;

import java.util.List;
import java.util.Set;

public interface ICartDetailService {
    CartDetail createCartDetail(Integer idCart,CartDetailDTO cartDetailDTO);
    List<CartDetail> getAllCartDetailByIdCart(Integer idCart);
    CartDetail deleteByIdCartDetail(Integer idCart, Integer idFruit);
    List<CartDetail> deleteByIdCart(Integer idCart);
    List<CartDetail> getAll();

}
