package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.CartDetail;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.id.CartDetailId;
import com.fruits.congtyhoaqua.repositories.CartDetailRepository;
import com.fruits.congtyhoaqua.repositories.CartRepository;
import com.fruits.congtyhoaqua.repositories.FruitRepository;
import com.fruits.congtyhoaqua.services.ICartDetailService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartDetailServiceImp implements ICartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private FruitRepository fruitRepository;

//    @Override
//    public CartDetail createCartDetail(Integer idCart, CartDetailDTO cartDetailDTO) {
//        Optional<Cart> cart = cartRepository.findById(idCart);
//        if (cart.isEmpty()){
//            throw new NotFoundException("No cart");
//        }
//        CartDetail cartDetail = new CartDetail();
//        Convert.fromCartDetailDTOToCartDetail(cartDetailDTO,cartDetail);
//        CartDetailId cartDetailId = new CartDetailId(idCart, cartDetailDTO.getIdFruit());
//        cartDetail.setCartDetailId(cartDetailId);
//        cartDetailRepository.save(cartDetail); // đang lỗi đoạn này
//        return cartDetail;
//    }

    @Override
    public CartDetail createCartDetail(Integer idCart, CartDetailDTO cartDetailDTO) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("No cart");
        }
        Optional<Fruit> fruit = fruitRepository.findById(cartDetailDTO.getIdFruit());
        CartDetail cartDetail = new CartDetail();
        Convert.fromCartDetailDTOToCartDetail(cartDetailDTO,cartDetail);
        CartDetailId cartDetailId = new CartDetailId(idCart, cartDetailDTO.getIdFruit());
        cartDetail.setCart(cart.get());
        cartDetail.setFruit(fruit.get());
        cartDetail.setCartDetailId(cartDetailId);
        cartDetailRepository.save(cartDetail);
        return cartDetail;
    }

    @Override
    public List<CartDetail> getAllCartDetailByIdCart(Integer idCart) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("No cart");
        }
        List<CartDetail> cartDetails = new ArrayList<>(cartDetailRepository.findAllByCart(idCart));
        return cartDetails;
    }

    @Override
    public List<CartDetail> getAll() {
        List<CartDetail> cartDetails = new ArrayList<>(cartDetailRepository.findAll());
        return cartDetails;
    }

    @Override
    public CartDetail deleteByIdCartDetail(Integer idCart, Integer idFruit) {
        CartDetailId cartDetailId = new CartDetailId(idCart, idFruit);
        Optional<CartDetail> cartDetail = cartDetailRepository.findById(cartDetailId);
        if (cartDetail.isEmpty()){
            throw new NotFoundException("No cartDetail");
        }
        cartDetailRepository.delete(cartDetail.get());
        return cartDetail.get();
    }

    @Override
    public List<CartDetail> deleteByIdCart(Integer idCart) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("No cart");
        }
        List<CartDetail> cartDetails = new ArrayList<>(cartDetailRepository.findAllByCart(idCart));
        for (CartDetail cartDetail: cartDetails) {
            if(cartDetail.getCartDetailId().getIdCart() != null){
                cartDetailRepository.delete(cartDetail);
            }
        }
        return cartDetails;

    }
}
