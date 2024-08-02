package com.example.blog.QT_Global_Blog.postcontroller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse<T> {
    private HttpStatus status;
    private T message;


}
