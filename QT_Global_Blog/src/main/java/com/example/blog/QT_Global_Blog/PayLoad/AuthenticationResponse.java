package com.example.blog.QT_Global_Blog.PayLoad;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

//return token as response
public class AuthenticationResponse {

    private String token;
}
