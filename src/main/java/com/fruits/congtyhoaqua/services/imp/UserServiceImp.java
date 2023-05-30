package com.fruits.congtyhoaqua.services.imp;

import com.cloudinary.api.exceptions.BadRequest;
import com.fruits.congtyhoaqua.dtos.UserDTO;
import com.fruits.congtyhoaqua.exceptions.BadRequestException;
import com.fruits.congtyhoaqua.exceptions.NotFoundException;
import com.fruits.congtyhoaqua.models.Cart;
import com.fruits.congtyhoaqua.models.Role;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.repositories.CartRepository;
import com.fruits.congtyhoaqua.repositories.RoleRepository;
import com.fruits.congtyhoaqua.repositories.UserRepository;
import com.fruits.congtyhoaqua.services.ICartService;
import com.fruits.congtyhoaqua.services.IUserService;
import com.fruits.congtyhoaqua.utils.Convert;
import com.fruits.congtyhoaqua.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class UserServiceImp implements IUserService {
    @Autowired private UserRepository userRepository;
    @Autowired private UploadFile uploadFile;
//    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private RoleRepository roleRepository;

    @Autowired private CartServiceImp cartServiceImp;

    @Autowired private CartRepository cartRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        User oldUser = userRepository.findByAccount(userDTO.getAccount());
        if(oldUser != null){
            throw new BadRequestException("Duplicate user");
        }
        User user = new User();
        Convert.fromUserDTOToUser(userDTO,user);
        userRepository.save(user);
        System.out.println("================================"+user.getId());
        Optional<Cart> cart = cartRepository.findByUser(user);
        if (!cart.isEmpty()){
            throw new NotFoundException("Cart is already exists");
        }
        cartServiceImp.createCart(user.getId());
        return user;
    }

    @Override
    public User editUser(Integer idUser, UserDTO userDTO) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
//        user.get().setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(Convert.fromUserDTOToUser(userDTO, user.get()));
    }

    @Override
    public User deleteUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
//        System.out.println("Hello WWorld liên tục lần 1");
//        List<Role> roles = roleRepository.findAll();
//        for (Role role: roles) {
//            Set<User> userss = role.getUsers();
//            System.out.println(userss.size());
//            userss.remove(user.get());
//            System.out.println(userss.size());
//            role.setUsers(userss);
//        }
//        System.out.println("Hello WWorld liên tục lần 2");
//        roleRepository.saveAll(roles);
//        System.out.println("Hello WWorld liên tục lần 5");
        user.get().setStatus(false);
        userRepository.save(user.get());
        return user.get();
    }

    @Override
    public User restoreUser(Integer idUser) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
//        List<Role> roles = roleRepository.findAll();
//        for (Role role: roles) {
//            Set<User> userss = role.getUsers();
//            System.out.println(userss.size());
//            userss.remove(user.get());
//            System.out.println(userss.size());
//            role.setUsers(userss);
//        }
//        roleRepository.saveAll(roles);
        user.get().setStatus(true);
        userRepository.save(user.get());
        return user.get();
    }



    @Override
    public List<User> findAllByName(String name) {
        List<User> users = userRepository.findAllByNameContaining(name);
        if(users.isEmpty()){
            throw new NotFoundException("No user");
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            throw  new NotFoundException("No user");
        }
        return user.get();
    }

    @Override
    public User editPassWord(Integer idUser, String password) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
//        user.get().setPassword(passwordEncoder.encode(password));
        return userRepository.save(user.get());
    }

    @Override
    public User editAvatar(Integer idUser, MultipartFile avatar) {
        Optional<User> user = userRepository.findById(idUser);
        if(user.isEmpty()){
            throw new NotFoundException("No user");
        }
        user.get().setAvatar(uploadFile.getUrlFromFile(avatar));
        return userRepository.save(user.get());
    }

//    @Override
//    public List<User> getAllUser() {
//        List<User> users = new ArrayList<>(userRepository.getAllUser());
//        if (users.isEmpty()){
//            throw new NotFoundException("No user.");
//        }
//        return users;
//    }


//    tất cả các tài khoản người dùng (khách hàng)
    @Override
    public List<User> selectAllUser(Integer start, Integer size) {
        List<User> users = new ArrayList<>(userRepository.selectAllUser(start,size));
        if (users.isEmpty()){
            throw new NotFoundException("No user.");
        }
        return users;
    }

    //    tất cả các tài khoản người dùng (khách hàng) đã xóa
    @Override
    public List<User> selectAllUserDelete() {
        List<User> users = new ArrayList<>(userRepository.selectAllUserDelete());
        if (users.isEmpty()){
            throw new NotFoundException("No user.");
        }
        return users;
    }

    //    tất cả các tài khoản người dùng (admin)
    @Override
    public List<User> selectAllAdmin() {
        List<User> users = new ArrayList<>(userRepository.selectAllAdmin());
        if (users.isEmpty()){
            throw new NotFoundException("No user.");
        }
        return users;
    }
}
