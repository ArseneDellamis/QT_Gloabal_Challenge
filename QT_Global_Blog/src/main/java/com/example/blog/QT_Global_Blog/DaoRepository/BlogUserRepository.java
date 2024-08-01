package com.example.blog.QT_Global_Blog.DaoRepository;

import com.example.blog.QT_Global_Blog.userEntities.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogUserRepository extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findByEmail(String email);
}
