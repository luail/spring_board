package com.example.board.board.author.service;

import com.example.board.board.author.domain.Author;
import com.example.board.board.author.dtos.AuthorDetailRes;
import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.author.dtos.AuthorSaveReq;
import com.example.board.board.author.dtos.AuthorUpdateReq;
import com.example.board.board.author.repository.AuthorRepository;
import com.example.board.board.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;

    public void save(AuthorSaveReq dto) throws IllegalArgumentException {
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Author author = authorRepository.save(dto.toEntity());
////        cascade를 활용하지 않고, 별도로 post 데이터 만드는 경우
//        postRepository.save(Post.builder().title("안녕하십니까").contents("신입이 인사드립니다").author(author).build());

//        cascade를 활용해서, post데이터를 함께 만드는 경우.
//        post를 생성하는 시점에 author가 아직 DB에 저장되지 않은것으로 보여지나,
//        jpa가 author와 post를 save하는 시점에 선후관계를 맞춰준다.
//        Author author = Author.builder().name(dto.getName()).email(dto.getEmail()).password(dto.getPassword()).role(dto.getRole()).build();
//        author.getPosts().add(Post.builder().title("안녕하세요1").author(author).build());
//        author.getPosts().add(Post.builder().title("안녕하세요2").author(author).build());
//        authorRepository.save(author);
    }

    public List<AuthorListRes> findAll() {
        return authorRepository.findAll().stream().map(a->a.listDtoFromEntity()).collect(Collectors.toList());
    }

    public void delete(Long id) throws EntityNotFoundException {
        Author author =authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("author is not found"));
        authorRepository.delete(author);
    }

    public AuthorDetailRes authorDetail(Long id) {
        return authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("author is not found")).detailDtoFromEntity();
    }

    public void update(Long id, AuthorUpdateReq authorUpdate) {
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("author is not found"));
        author.updateProfile(authorUpdate);
//        기존객체에 변경이 발생할 경우, 별도의 save 없이도 jpa가 엔티티의 변경을 자동인지하고, 변경사항을 DB반영
//        이를 dirtychecking이라 부르고,  반드시 Transactional어노테이션이 있을 경우 동직.
//        authorRepository.save(author);
    }
}
