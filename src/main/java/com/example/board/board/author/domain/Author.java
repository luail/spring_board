package com.example.board.board.author.domain;

import com.example.board.board.author.dtos.AuthorDetail;
import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.author.dtos.AuthorUpdate;
import com.example.board.board.common.domain.BaseTimeEntity;
import com.example.board.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

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
//    OneToMany의 기본값이 fetch lazy라 별도의 설정은 없다.
//    mappedBy에 ManyToOne쪽의 변수명을 문자열로 지정.
    @OneToMany(mappedBy = "author", cascade = ALL)
//    빌더패턴에서 변수 초기화(디폴트값) 시 Builder.Default 어노테이션 사용
    @Builder.Default
    private List<Post> posts = new ArrayList<>();

    public AuthorListRes listDtoFromEntity() {
        return AuthorListRes.builder().id(this.id).name(this.name).email(this.email).build();
    }

    public AuthorDetail detailDtoFromEntity() {
        return AuthorDetail.builder().id(this.id).name(this.name).email(this.email).password(this.password).role(this.role).postCount(this.posts.size()).createdTime(this.getCreateTime()).build();
    }

    public void updateProfile(AuthorUpdate authorUpdate) {
        this.name = authorUpdate.getName();
        this.password = authorUpdate.getPassword();
    }
}
