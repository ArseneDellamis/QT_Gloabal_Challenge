package com.example.blog.QT_Global_Blog.DaoRepository;

import com.example.blog.QT_Global_Blog.userEntities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// data access object for role table. used for accessing database table role using Java persistent Api
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
