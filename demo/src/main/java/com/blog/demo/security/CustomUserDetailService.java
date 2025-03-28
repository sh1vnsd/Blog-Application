package com.blog.demo.security;

import com.blog.demo.exceptions.CustomUsernameNotFoundException;
import com.blog.demo.exceptions.ResourceNotFoundException;
import com.blog.demo.models.User;
import com.blog.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {

//    @Autowired
//    private UserRepo userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        //loading user from database by username
//        User user = this.userRepo.findByEmail(username)
//                .orElseThrow(() -> new CustomUsernameNotFoundException("User", "email", username));
//        return user;
//    }

    @Autowired
    private UserRepo userRepo;
    @Override
    // returns the user
    public UserDetails loadUserByUsername(String email) throws ResourceNotFoundException {
        System.out.println("email: " + email); // this is not getting us the username of the user after the user tried to access a resource using the access_token
        User user = this.userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User", "Email : " + email, 0));
        return user;
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                Collections.emptyList()
//        );
    }
}
