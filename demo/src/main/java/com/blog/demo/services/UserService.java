package com.blog.demo.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.blog.demo.models.User;
import com.blog.demo.payloads.UserDto;


public interface UserService {
    

    UserDto registerNewUser(UserDto userDto);

    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer userId);

    UserDto getUserById(Integer userId);

    List<UserDto> getAllUsers();

    void deleteUser(Integer userId);
    
}
