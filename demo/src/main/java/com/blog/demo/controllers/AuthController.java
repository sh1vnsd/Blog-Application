package com.blog.demo.controllers;

import com.blog.demo.exceptions.ApiException;
import com.blog.demo.payloads.JwtAuthRequest;
import com.blog.demo.payloads.JwtAuthResponse;
import com.blog.demo.security.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //to generate token we need

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    //Used to authenticate password and username

    @Autowired
    private AuthenticationManager authenticationManager;
    //This above is all we need to generate token


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
            ) throws Exception {
            //We will get the username & password in request to authenticate

        this.authenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());

        System.out.println("userDetails: " + userDetails.getUsername());
        String token = this.jwtTokenHelper.generateToken(userDetails.getUsername());

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);

        return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {

        //Created authentication token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            try{
                this.authenticationManager.authenticate(authenticationToken);
            }
            catch (BadCredentialsException e){
                System.out.println("Invalid Details !!");
                throw new ApiException("Invalid username or password !!");
            }
    }
}
