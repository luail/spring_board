package com.example.board.board.author.service;

import com.example.board.board.author.domain.Author;
import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.author.dtos.AuthorSaveReq;
import com.example.board.board.author.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public void save(AuthorSaveReq authorSaveReq) throws IllegalArgumentException {
        if (authorRepository.findByEmail(authorSaveReq.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        authorRepository.save(authorSaveReq.toEntity());
    }

    public List<AuthorListRes> findAll() {
        return authorRepository.findAll().stream().map(a->a.listDtoFromEntity()).collect(Collectors.toList());
    }
}
