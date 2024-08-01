package com.example.blog.QT_Global_Blog.service;

import com.example.blog.QT_Global_Blog.AuthenticationConfig.JwtService;
import com.example.blog.QT_Global_Blog.DaoRepository.BlogUserRepository;
import com.example.blog.QT_Global_Blog.DaoRepository.RoleRepository;
import com.example.blog.QT_Global_Blog.PayLoad.AuthenticationRequest;
import com.example.blog.QT_Global_Blog.PayLoad.AuthenticationResponse;
import com.example.blog.QT_Global_Blog.PayLoad.RegisterRequest;
import com.example.blog.QT_Global_Blog.userEntities.BlogUser;
import com.example.blog.QT_Global_Blog.userEntities.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final BlogUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService service;
    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Role> getRole= roleRepository.findByName("USER");
        if (getRole.isEmpty()) {
            return null;
        }
        var user = BlogUser.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(getRole.get())
                .build();
        repository.save(user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
       manager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       request.getEmail(),
                       request.getPassword()
               )
       );
       var user = repository.findByEmail(request.getEmail())
               .orElseThrow(
                       ()-> new UsernameNotFoundException("user not found")
               );
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
