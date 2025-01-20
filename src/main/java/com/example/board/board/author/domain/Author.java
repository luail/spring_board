package com.example.board.board.author.domain;

import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false)
    private String name;
    @Column(length = 30, nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
//    enum은 기본적으로 숫자값으로 db에 들어감으로, 별도로 STRING지정 필요.
    @Enumerated(EnumType.STRING)
    private Role role;

    public AuthorListRes listDtoFromEntity() {
        return AuthorListRes.builder().id(this.id).name(this.name).email(this.email).build();
    }
}
