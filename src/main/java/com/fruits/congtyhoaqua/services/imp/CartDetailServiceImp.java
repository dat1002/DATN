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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Set<CartDetail> getAllCartDetailByIdCart(Integer idCart) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("No cart");
        }
        Set<CartDetail> cartDetails = new HashSet<>(cartDetailRepository.findAllByCart(cart.get()));
        return cartDetails;
    }

    @Override
    public Set<CartDetail> getAll() {
        Set<CartDetail> cartDetails = new HashSet<>(cartDetailRepository.findAll());
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
    public Set<CartDetail> deleteByIdCart(Integer idCart) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("No cart");
        }
        Set<CartDetail> cartDetails = new HashSet<>(cartDetailRepository.findAllByCart(cart.get()));
        for (CartDetail cartDetail: cartDetails) {
            if(cartDetail.getCartDetailId().getIdCart() != null){
                cartDetailRepository.delete(cartDetail);
            }
        }
        return cartDetails;

    }
}
