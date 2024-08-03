package com.example.blog.QT_Global_Blog.DaoRepository;


import com.example.blog.QT_Global_Blog.postEntity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// data access object for role table. used for accessing database table Post using Java persistent Api
public interface PostRepository extends JpaRepository<Post, Long> {
}
