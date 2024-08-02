package com.example.blog.QT_Global_Blog.postcontroller;

import com.blogApp.blogApp.DaoRepository.CommentRepository;
import com.blogApp.blogApp.DaoRepository.PostRepository;
import com.blogApp.blogApp.postEntity.Comment;
import com.blogApp.blogApp.postEntity.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PageController {

    private final PostRepository postRepo;
    private final CommentRepository commentRepo;

    public PageController(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @GetMapping("/create-post")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Post());
        return "postPage";
    }

    @GetMapping("/home")
    public String showHomePage(Model model) {
        List<Post> posts = postRepo.findAll();
        posts.forEach(post -> {
            long commentCount = commentRepo.countByPostId(post.getId());
            post.setCommentCount(commentCount);
        });
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post", post);
        model.addAttribute("comments", commentRepo.findAllByPostId(id));
        model.addAttribute("newComment", new Comment());
        return "postDetails";
    }

}

