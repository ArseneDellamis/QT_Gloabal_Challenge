package com.example.blog.QT_Global_Blog.PayLoad;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Authentication request take in email, password
public class AuthenticationRequest {

    private String email;
    private String password;
}
