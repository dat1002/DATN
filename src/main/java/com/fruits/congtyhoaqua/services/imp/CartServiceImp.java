package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.CartDetailDTO;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.CartDetail;
import com.fruits.congtyhoaqua.models.Fruit;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.models.id.CartDetailId;
import com.fruits.congtyhoaqua.repositories.CartDetailRepository;
import com.fruits.congtyhoaqua.repositories.CartRepository;
import com.fruits.congtyhoaqua.repositories.FruitRepository;
import com.fruits.congtyhoaqua.repositories.UserRepository;
import com.fruits.congtyhoaqua.services.ICartService;
import com.fruits.congtyhoaqua.utils.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImp implements ICartService {

    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private FruitRepository fruitRepository;
    @Autowired
    private CartDetailServiceImp cartDetailServiceImp;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart createCart(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("User does not exist");
        }
        Optional<Cart> cart = cartRepository.findByUser(user.get());
        if (!cart.isEmpty()){
            throw new NotFoundException("Cart is already exists");
        }
        Cart cart1 = new Cart();
        cart1.setUser(user.get());
        System.out.printf("cart1="+cart1.getId());
        return cartRepository.save(cart1);
    }

    @Override
    public Cart getCartByIdUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("User does not exist");
        }
        Optional<Cart> cart = cartRepository.findByUser(user.get());
        if (cart.isEmpty()){
            throw new NotFoundException("Cart does not exist");
        }

        return cart.get();
    }

    @Override
    public Cart getCartById(Integer idCart) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("Cart does not exist");
        }
        return cart.get();
    }

    @Override
    public Cart editCart(Integer idCart, Set<CartDetailDTO> cartDetailDTOS) {
        Optional<Cart> cart = cartRepository.findById(idCart);
        if (cart.isEmpty()){
            throw new NotFoundException("Cart does not exist");
        }
        Integer totalMoney = 0;
        for(CartDetailDTO cartDetailDTO: cartDetailDTOS){
            cartDetailServiceImp.createCartDetail(idCart,cartDetailDTO);
            Optional<Fruit> fruit = fruitRepository.findById(cartDetailDTO.getIdFruit());
            totalMoney+= fruit.get().getPriceOut()*cartDetailDTO.getAmount();
        }
        cart.get().setIntoMoney(totalMoney);
        cartRepository.save(cart.get());
        return cart.get();
    }
}
