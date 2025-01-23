package com.example.board.board.author;

import com.example.board.board.author.domain.Author;
import com.example.board.board.author.domain.Role;
import com.example.board.board.author.dtos.AuthorSaveReq;
import com.example.board.board.author.repository.AuthorRepository;
import com.example.board.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthorServiceTest {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest() {
        AuthorSaveReq dto = new AuthorSaveReq("ko", "jonnnnnon@naver.com", "11", Role.ADMIN);
        authorService.save(dto);

        Author author = authorRepository.findByEmail(dto.getEmail()).orElse(null);
        Assertions.assertEquals(dto.getEmail(), author.getEmail());
    }

    @Test
    public void authorFindAllTest() {
        int beforeSize = authorService.findAll().size();
        authorRepository.save(Author.builder().name("aaaaaaaa").email("1aaaa@naver.com").password("11111").build());
        authorRepository.save(Author.builder().name("aaaaaaaa").email("2aaadda@naver.com").password("11111").build());
        authorRepository.save(Author.builder().name("aaaaaaaa").email("3aassaaa@naver.com").password("11111").build());
        authorRepository.save(Author.builder().name("aaaaaaaa").email("4agggaaa@naver.com").password("11111").build());
        int afterSize = authorService.findAll().size();

        Assertions.assertEquals(beforeSize+4, afterSize);
    }
}
