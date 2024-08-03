package com.example.blog.QT_Global_Blog.DaoRepository;


import com.example.blog.QT_Global_Blog.postEntity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// data access object for role table. used for accessing database table role using Java persistent Api
public interface CommentRepository extends JpaRepository<Comment, Long> {

//    query to get all comment by post
    @Query("SELECT c.commentContent AS commentContent FROM Comment c WHERE c.post.id = :postId")
    List<String> findAllCommentByPostId(@Param("postId") long postId);
//query to count comments on a post
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.id = :postId")
    long countByPostId(@Param("postId") long postId);
}

