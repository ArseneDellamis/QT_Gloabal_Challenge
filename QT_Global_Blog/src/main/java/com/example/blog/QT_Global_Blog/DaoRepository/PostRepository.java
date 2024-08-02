package com.blogApp.blogApp.DaoRepository;

import com.blogApp.blogApp.postEntity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
