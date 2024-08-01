package com.example.blog.QT_Global_Blog.controller;

import com.example.blog.QT_Global_Blog.DaoRepository.BlogUserRepository;
import com.example.blog.QT_Global_Blog.DaoRepository.RoleRepository;
import com.example.blog.QT_Global_Blog.userEntities.BlogUser;
import com.example.blog.QT_Global_Blog.userEntities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final BlogUserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/all-users")
    public ResponseEntity<List<BlogUser>> findAll() {
            List<BlogUser> allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping("/create-user")
    public ResponseEntity<BlogUser> createUser(@RequestBody BlogUser blogUser) {

//            check if the email already exists
        Optional<BlogUser> existingUser =
                userRepository
                        .findByEmail(blogUser.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Optional<Role> getRole= roleRepository.findByName("USER");
        if (getRole.isEmpty()){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        blogUser.setRole(getRole.get());
        BlogUser newUser = userRepository.save(blogUser);
//      save a new user if email does not exist
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
