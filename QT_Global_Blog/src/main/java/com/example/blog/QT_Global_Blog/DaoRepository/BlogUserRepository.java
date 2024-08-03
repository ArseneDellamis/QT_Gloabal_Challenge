package com.example.blog.QT_Global_Blog.DaoRepository;

import com.example.blog.QT_Global_Blog.userEntities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// data access object for role table. used for accessing database table BlogUser using Java persistent Api

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findByEmail(String email);
}
