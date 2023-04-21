package com.fruits.congtyhoaqua.controllers;

import com.fruits.congtyhoaqua.bases.BaseController;
import com.fruits.congtyhoaqua.dtos.UserDTO;
import com.fruits.congtyhoaqua.filters.AuthenticationResponse;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.payload.AuthenticationRequest;
import com.fruits.congtyhoaqua.services.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.InvalidObjectException;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController<AuthenticationResponse> {

    @Autowired
    private IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        return this.resSuccess(authService.login(authenticationRequest));
    }

    @PostMapping("/signup-user")
    @PreAuthorize("hasRole('ADMIN')")

    public ResponseEntity<?> signupAdmin(@Valid @RequestBody UserDTO userDTO){
        return this.resSuccess(authService.signupAdmin(userDTO));
    }

    @PostMapping("/signup-user2")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> signUpUser2(@Valid @RequestBody UserDTO userDTO){
        return this.resSuccess(authService.signupUser2(userDTO));
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody AuthenticationResponse authenticationResponse) throws InvalidObjectException {
        return this.resSuccess(authService.validateToken(authenticationResponse));
    }
}

