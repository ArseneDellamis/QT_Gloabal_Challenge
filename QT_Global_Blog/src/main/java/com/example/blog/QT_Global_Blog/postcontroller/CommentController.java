package com.example.blog.QT_Global_Blog.postcontroller;


import com.example.blog.QT_Global_Blog.DaoRepository.CommentRepository;
import com.example.blog.QT_Global_Blog.DaoRepository.PostRepository;
import com.example.blog.QT_Global_Blog.ResponseHandler.Response;
import com.example.blog.QT_Global_Blog.postEntity.Comment;
import com.example.blog.QT_Global_Blog.postEntity.Post;
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
    public ResponseEntity<Response> commentOnPost(@RequestBody Comment comment, @PathVariable long postId) {
        Optional<Post> getPost = postRepo.findById(postId);
        if (getPost.isEmpty()) {
            throw new RuntimeException("Post Not Found!");
        }
        Post post = getPost.get();
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        Comment newComment = commentRepo.save(comment);
        Response<Comment> commentResponse= new Response<>();
        commentResponse.setStatus(HttpStatus.CREATED);
        commentResponse.setMessage("comment Added");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
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
    public ResponseEntity<Response> deleteComment(@PathVariable Long id) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepo.delete(comment);
        Response<Comment> response= new Response<>();
        response.setStatus(HttpStatus.OK);
        response.setMessage("Comment deleted successfully");
        return ResponseEntity.ok(response);
    }
}
