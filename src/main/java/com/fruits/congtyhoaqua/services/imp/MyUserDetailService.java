package com.fruits.congtyhoaqua.services.imp;

import com.fruits.congtyhoaqua.models.User;
import com.fruits.congtyhoaqua.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByAccount(username);
        if (user == null){
            throw new UsernameNotFoundException("Not found user"+ username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(item ->{
            grantedAuthorities.add(new SimpleGrantedAuthority(item.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getAccount(),user.getPassword(),grantedAuthorities);
    }
}
