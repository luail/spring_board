package com.example.board.board.post.domain;

import com.example.board.board.author.domain.Author;
import com.example.board.board.common.domain.BaseTimeEntity;
import com.example.board.board.post.dtos.PostDetailRes;
import com.example.board.board.post.dtos.PostListRes;
import com.example.board.board.post.dtos.PostUpdateReq;
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
    private String appointment;
    private LocalDateTime appointmentTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    public PostListRes postListResFromEntity() {
        return PostListRes.builder().id(this.id).title(this.title).authorEmail(this.author.getEmail()).build();
    }

    public PostDetailRes detailResFromEntity() {
        return PostDetailRes.builder().id(this.id).title(this.title).contents(this.contents).authorEmail(this.author.getEmail()).createdTime(this.getCreateTime()).updatedTime(this.getUpdateTime()).build();
    }

    public void update(PostUpdateReq dto) {
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }

    public void updateAppointmentToN(String appointment) {
        this.appointment = appointment;
    }
 }
