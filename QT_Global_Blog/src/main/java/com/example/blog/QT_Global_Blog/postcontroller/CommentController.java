package com.blogApp.blogApp.controller;

import com.blogApp.blogApp.DaoRepository.CommentRepository;
import com.blogApp.blogApp.DaoRepository.PostRepository;
import com.blogApp.blogApp.postEntity.Comment;
import com.blogApp.blogApp.postEntity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepo;
    private final PostRepository postRepo;

    @PostMapping("/{postId}")
    public ResponseEntity<String> commentOnPost(@RequestBody Comment comment, @PathVariable long postId) {
        Optional<Post> getPost = postRepo.findById(postId);
        if (getPost.isEmpty()) {
            throw new RuntimeException("Post Not Found!");
        }
        Post post = getPost.get();
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment newComment = commentRepo.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("comment Added");
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<String>> getAllCommentsByPostId(@PathVariable long postId) {
        List<String> comments = commentRepo.findAllCommentByPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Comment comment1 = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        comment1.setPost(comment.getPost());
        comment1.setCommentContent(comment.getCommentContent());
        comment1.setPostedBy(comment.getPostedBy());
        comment1.setCreatedAt(LocalDateTime.now());
        comment1.setUpdatedAt(LocalDateTime.now());

        return ResponseEntity.ok(commentRepo.save(comment));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}
