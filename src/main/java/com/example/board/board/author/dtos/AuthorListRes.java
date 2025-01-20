package com.example.board.board.author.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class AuthorListRes {
    private Long id;
    private String name;
    private String email;
}
