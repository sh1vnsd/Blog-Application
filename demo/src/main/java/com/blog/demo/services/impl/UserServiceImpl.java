package com.blog.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.blog.demo.config.AppConstants;
import com.blog.demo.models.Role;
import com.blog.demo.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blog.demo.models.User;
import com.blog.demo.payloads.UserDto;
import com.blog.demo.repository.UserRepo;
import com.blog.demo.services.UserService;
import com.blog.demo.exceptions.*;

@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        //Here we are encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        //roles
        Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();

        user.getRoles().add(role);

        User newUser = this.userRepo.save(user);


        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
   }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        //Get
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));

        //Set
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        
        //Update
        User updatedUser = this.userRepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", " Id ", userId));
        
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        
        this.userRepo.delete(user);
    }


    //Below implementations can be done with the help of ModelMapper
    //Entity to DTO
    private User dtoToUser(UserDto userDto){
            // User user = new User();
            // user.setId(userDto.getId());
            // user.setName(userDto.getName());
            // user.setEmail(userDto.getEmail());
            // user.setAbout(userDto.getAbout());
            // user.setPassword(userDto.getPassword());
            // return user;

            User user = this.modelMapper.map(userDto, User.class);
            return user;
    }

    //User To DTO
    public UserDto userToDto(User user){
        // UserDto userDto = new UserDto();
        // userDto.setId(user.getId());
        // userDto.setName(user.getName());
        // userDto.setEmail(user.getEmail());
        // userDto.setAbout(user.getAbout());
        // userDto.setPassword(user.getPassword());
        // return userDto;

        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}