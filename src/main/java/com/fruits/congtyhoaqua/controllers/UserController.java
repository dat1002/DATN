package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.UserDTO;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.services.IUserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController  extends BaseController<User> {
    @Autowired
    private IUserService userService;

    @PostMapping("/create")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        return this.resSuccess(userService.createUser(userDTO));

    }

    @PatchMapping("/editUser/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editUser(@PathVariable Integer idUser, @RequestBody UserDTO userDTO){
        return this.resSuccess(userService.editUser(idUser, userDTO));
    }

    @DeleteMapping("/delete/{idUser}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Integer idUser){
        return this.resSuccess(userService.deleteUser(idUser));
    }

    @GetMapping("/get-all-by-name")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> findAllByName(@RequestParam String name){
        return  this.resListSuccess(userService.findAllByName(name));
    }

    @GetMapping("/get-by-id/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> findById(@PathVariable Integer idUser){
        return this.resSuccess(userService.findById(idUser));
    }

    @PatchMapping("/edit-password/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editPassWord(@PathVariable Integer idUser, @RequestParam String password){
        return this.resSuccess(userService.editPassWord(idUser, password));
    }

    @PatchMapping("/edit-avatar/{idUser}")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> editAvatar(@PathVariable Integer idUser, @RequestParam MultipartFile avatar){
        return  this.resSuccess(userService.editAvatar(idUser, avatar));
    }


    // tất cả tài khoản của khách hàng
    @GetMapping("/get-all-user/{start}/{size}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUser(@PathVariable(name = "start")Integer start,
                                        @PathVariable(name = "size")Integer size){
        return this.resListSuccess(userService.selectAllUser(start,size));
    }

    // tất cả tài khoản của khách hàng đã xóa
    @GetMapping("/get-all-user-delete")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUserDelete(){
        return this.resListSuccess(userService.selectAllUserDelete());
    }

    @GetMapping("/get-all-admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAdmin(){
        return this.resListSuccess(userService.selectAllAdmin());
    }
}
