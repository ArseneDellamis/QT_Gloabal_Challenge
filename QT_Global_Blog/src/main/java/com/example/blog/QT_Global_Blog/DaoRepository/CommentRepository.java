package com.example.blog.QT_Global_Blog.DaoRepository;


import com.example.blog.QT_Global_Blog.postEntity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c.commentContent AS commentContent FROM Comment c WHERE c.post.id = :postId")
    List<String> findAllCommentByPostId(@Param("postId") long postId);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post.id = :postId")
    long countByPostId(@Param("postId") long postId);
}

