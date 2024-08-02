package com.blogApp.blogApp.controller;

import com.blogApp.blogApp.DaoRepository.CommentRepository;
import com.blogApp.blogApp.DaoRepository.PostRepository;
import com.blogApp.blogApp.postEntity.Comment;
import com.blogApp.blogApp.postEntity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepo;

    private final CommentRepository commentRepo;

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> getAll = postRepo.findAll();
        return ResponseEntity.ok(getAll);
    }

    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        Post newPost = postRepo.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setAuthor(post.getAuthor());
        post.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(postRepo.save(post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepo.delete(post);
        return ResponseEntity.ok("Post deleted successfully");
    }
}
