package com.example.blog.QT_Global_Blog.DaoRepository;


import com.example.blog.QT_Global_Blog.postEntity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
