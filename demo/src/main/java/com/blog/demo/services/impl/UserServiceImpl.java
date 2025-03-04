package com.blog.demo.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.blog.demo.models.User;
import com.blog.demo.payloads.UserDto;
import com.blog.demo.repository.UserRepo;
import com.blog.demo.services.UserService;

public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
   }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        
    }

    @Override
    public UserDto getUserById(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    }

    @Override
    public void deleteUser(Integer userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    //Entity to DTO
    private User dtoToUser(UserDto userDto){
            User user = new User();
            user.setId(userDto.getId());
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setAbout(userDto.getAbout());
            user.setPassword(userDto.getPassword());
            return user;
    }

    //User To DTO
    public UserDto userToDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}