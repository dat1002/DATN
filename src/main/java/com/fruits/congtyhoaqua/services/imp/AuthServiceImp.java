package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.dtos.UserDTO;
import com.fruits.congtyhoaqua.exceptions.BadRequestException;
import com.fruits.congtyhoaqua.filters.AuthenticationResponse;
import com.fruits.congtyhoaqua.models.Role;
import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.payload.AuthenticationRequest;
import com.fruits.congtyhoaqua.repositories.RoleRepository;
import com.fruits.congtyhoaqua.repositories.UserRepository;
import com.fruits.congtyhoaqua.services.IAuthService;
import com.fruits.congtyhoaqua.services.imp.MyUserDetailService;
import com.fruits.congtyhoaqua.utils.Convert;
import com.fruits.congtyhoaqua.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImp implements IAuthService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(), authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Incorrect username or password");
        }
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        User user = userRepository.findByAccount(authenticationRequest.getUsername());
        List<String> roles = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (roleSet.size() > 0) {
            roleSet.forEach(item -> roles.add(item.getName()));
        }
        return new AuthenticationResponse(jwt, user.getId(), user.getAccount(), roles);
    }

    @Override
    public AuthenticationResponse signupUser(UserDTO userDTO) {
        User oldUser = userRepository.findByAccount(userDTO.getAccount());
        if (oldUser != null) {
            throw new BadRequestException("Username exists");
        }
        User user = new User();
        Convert.fromUserDTOToUser(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUsers();
        users.add(user);
        role.setUsers(users);
        roleRepository.save(role);

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getAccount());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getAccount(), List.of(role.getName()));
    }

    @Override
    public AuthenticationResponse signupUser2(UserDTO userDTO) {
        User oldUser = userRepository.findByAccount(userDTO.getAccount());
        if (oldUser != null) {
            throw new BadRequestException("User name exists");
        }
        User user = new User();
        Convert.fromUserDTOToUser(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER2");
        user.setRoles(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUsers();
        users.add(user);
        role.setUsers(users);
        roleRepository.save(role);
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getAccount());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getAccount(), List.of(role.getName()));
    }

    @Override
    public AuthenticationResponse signupAdmin(UserDTO userDTO) {
        User oldUser = userRepository.findByAccount(userDTO.getAccount());
        if (oldUser != null) {
            throw new BadRequestException("Username exists");
        }
        User user = new User();
        Convert.fromUserDTOToUser(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(Set.of(role));
        User newUser = userRepository.save(user);
        Set<User> users = role.getUsers();
        users.add(user);
        role.setUsers(users);
        roleRepository.save(role);

        final UserDetails userDetails = myUserDetailService.loadUserByUsername(newUser.getAccount());
        final String jwt = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwt, newUser.getId(), newUser.getAccount(), List.of(role.getName()));
    }

    @Override
    public AuthenticationResponse validateToken(AuthenticationResponse authenticationResponse) throws InvalidObjectException {
        try {
            String jwt = authenticationResponse.getJwt();
            String username = jwtUtil.extractUsername(jwt);
            UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            User user = userRepository.findByAccount(username);
            Set<Role> roles = user.getRoles();
            return new AuthenticationResponse(
                    jwtUtil.generateToken(userDetails),
                    user.getId(),
                    user.getAccount(),
                    roles.stream().map(Role::getName).collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new InvalidObjectException(e.getMessage());
        }
    }

}

