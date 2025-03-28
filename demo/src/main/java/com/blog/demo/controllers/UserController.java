package com.blog.demo.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.demo.payloads.ApiResponse;
import com.blog.demo.payloads.UserDto;
import com.blog.demo.services.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //To handle POST -> Create User
    @PostMapping("/")                         //Using valid to enable Validations used in UserDto  
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
        
    }
    //To handle PUT -> Update User
                   //This is path uri variable 
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uId){
        UserDto updatedUser = this.userService.updateUser(userDto, uId);
        return ResponseEntity.ok(updatedUser);
    }

    //ADMIN
    //To handle DELETE -> Delete user
    @PreAuthorize(value = "hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
            //use ? if you dont know the type
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uId){
         this.userService.deleteUser(uId);
         return new ResponseEntity(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }
    //To handle GET -> Get user

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
