package com.blog.demo.payloads;



import com.blog.demo.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    
    private int id;

    @NotEmpty
    @Size(min = 4, message ="Username to short !!")
    private String name;

    @Email(message = "Email address is not valid !!")
    private String email;

    @NotEmpty
    @Size(min = 4, message = "Password to short !!")
    private String password;

    @NotEmpty
    private String about;


    private Set<RoleDto> roles = new HashSet<>();
}