package com.fruits.congtyhoaqua.services;

import com.fruits.congtyhoaqua.dtos.UserDTO;
import com.fruits.congtyhoaqua.models.User;
import io.swagger.models.auth.In;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface IUserService {
    User createUser(UserDTO userDTO);
    User editUser(Integer idUser, UserDTO userDTO);
    User deleteUser(Integer idUser);
    Set<User> findAllByName(String name);
    User findById(Integer id);
    User editPassWord(Integer idUser, String password);
    User editAvatar(Integer idUser, MultipartFile avatar);
    Set<User> getAllUser();

}
