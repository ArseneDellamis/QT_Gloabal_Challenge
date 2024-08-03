package com.example.blog.QT_Global_Blog.postcontroller;


import com.example.blog.QT_Global_Blog.DaoRepository.CommentRepository;
import com.example.blog.QT_Global_Blog.DaoRepository.PostRepository;
import com.example.blog.QT_Global_Blog.ResponseHandler.Response;
import com.example.blog.QT_Global_Blog.postEntity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor

//RestAPi for posting and endpoint accessible for and authenticated user
public class PostController {
    private final PostRepository postRepo;

//    get all Posts http://localhost:8080/api/posts
    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts() {
        List<Post> getAll = postRepo.findAll();
        return ResponseEntity.ok(getAll);
    }

    // creating a post http://localhost:8080/api/posts/create-post
    @PostMapping("/create-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        Post newPost = postRepo.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }


    //    update post by id http://localhost:8080/api/posts/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) { // passing post id to update , and body
        //checking if post exists or not
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(postDetails.getTitle());
        post.setContent(postDetails.getContent());
        post.setAuthor(post.getAuthor());
        post.setUpdatedAt(LocalDateTime.now());
        return ResponseEntity.ok(postRepo.save(post));
    }

    // delete post by id http://localhost:8080/api/posts/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deletePost(@PathVariable Long id) {//passing the post id to delete as parameter
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        postRepo.delete(post);
        Response<Post> postResponse = new Response<>();
        postResponse.setStatus(HttpStatus.OK);
        postResponse.setMessage("Post deleted successfully");
        return ResponseEntity.ok(postResponse);
    }
}
