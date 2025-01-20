package com.example.board.board.post.domain;

import com.example.board.board.author.domain.Author;
import com.example.board.board.common.domain.BaseTimeEntity;
import com.example.board.board.post.dtos.PostListRes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 3000)
    private String contents;
    private String appoinment;
    private LocalDateTime appointmentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public PostListRes postListResFromEntity() {
        return PostListRes.builder().id(this.id).title(this.title).authorEmail(this.author.getEmail()).build();
    }
}
