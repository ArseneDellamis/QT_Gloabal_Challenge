package com.example.blog.QT_Global_Blog.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//registration request class
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
