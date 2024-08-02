package com.blogApp.blogApp.postEntity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(columnDefinition = "TEXT")
    private String commentContent;

    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
