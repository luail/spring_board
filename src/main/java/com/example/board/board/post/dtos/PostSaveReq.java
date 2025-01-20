package com.example.board.board.post.dtos;

import com.example.board.board.author.domain.Author;
import com.example.board.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

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

    public Post toEntity(Author author) {
        return Post.builder().title(this.title).contents(this.contents).author(author).build();
    }
}
