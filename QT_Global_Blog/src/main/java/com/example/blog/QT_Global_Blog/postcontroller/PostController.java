package com.blogApp.blogApp.controller;

import com.blogApp.blogApp.DaoRepository.CommentRepository;
import com.blogApp.blogApp.DaoRepository.PostRepository;
import com.blogApp.blogApp.postEntity.Comment;
import com.blogApp.blogApp.postEntity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/api/posts")
//@RequiredArgsConstructor
public class PostController {
    private final PostRepository postRepo;

    private final CommentRepository commentRepo;

    public PostController(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        model.addAttribute("comments", commentRepo.findAllByPostId(id));
        model.addAttribute("newComment", new Comment());
        return "postDetails";
    }


    @PostMapping("/{id}/add-comment")
    public String addComment(@PathVariable Long id, @ModelAttribute Comment newComment, Model model) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        newComment.setPost(post);
        newComment.setCreatedAt(LocalDateTime.now());
        newComment.setUpdatedAt(LocalDateTime.now());
        commentRepo.save(newComment);

        return "redirect:/home";
    }



    @GetMapping("/create-post")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "postPage";
    }

    @PostMapping("/create-post")
    public String createPost(@ModelAttribute Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postRepo.save(post);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "homePage";
    }



























//    @GetMapping
//    public ResponseEntity<List<Post>> findAllPosts() {
//        List<Post> getAll = postRepo.findAll();
//        return ResponseEntity.ok(getAll);
//    }
//
//    @PostMapping("/create-post")
//    public ResponseEntity<Post> createPost(@RequestBody Post post) {
//        post.setCreatedAt(LocalDateTime.now());
//        post.setUpdatedAt(LocalDateTime.now());
//        Post newPost = postRepo.save(post);
//        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
//        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
//        post.setTitle(postDetails.getTitle());
//        post.setContent(postDetails.getContent());
//        post.setUpdatedAt(LocalDateTime.now());
//        return ResponseEntity.ok(postRepo.save(post));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deletePost(@PathVariable Long id) {
//        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
//        postRepo.delete(post);
//        return ResponseEntity.ok("Post deleted successfully");
//    }
}
