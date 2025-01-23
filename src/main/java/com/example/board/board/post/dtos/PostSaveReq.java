package com.example.board.board.post.dtos;

import com.example.board.board.author.domain.Author;
import com.example.board.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
// @Builder 보통 Req에는 Builder를 필요로 하지않음.
public class PostSaveReq {
    @NotEmpty
    private String title;
    private String contents;
    @NotEmpty
    private String email;
    private String appointment;
    private String appointmentTime;

    public Post toEntity(Author author, LocalDateTime appointmentTime) {
        return Post.builder().title(this.title).contents(this.contents).author(author).appointment(this.appointment).appointmentTime(appointmentTime).build();
    }

}
