package com.blog.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blog.demo.models.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
