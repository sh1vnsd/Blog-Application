package com.blog.demo.models;

import java.util.*;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import org.hibernate.annotations.Collate;
import org.springframework.beans.factory.annotation.Autowire;
import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false, length = 100)
    private String name;

    private String email;

    private String password;

    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    //One user can have many roles or one user can be treated as many roles (he can be a admin & he also can be normal user
    //And also one single role can be associated with many users

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)


    @JoinTable(name = "user_role", //to manage this relationship we need a third table so here we give the name of third table
    joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
            //means in the table what will be the column of the user Id,
            //refernceColName means in this table what will be primary key that will work as foreign key in other table
    inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
            //Name of the THING which will keep the id of role in this table
            //In role table which primary key is working as foreign key in user table

    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
