package com.example.board.board.author.controller;

import com.example.board.board.author.dtos.AuthorDetailRes;
import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.author.dtos.AuthorSaveReq;
import com.example.board.board.author.dtos.AuthorUpdateReq;
import com.example.board.board.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/create")
    public String authorCreate(@ModelAttribute @Valid AuthorSaveReq authorSaveReq) {
        authorService.save(authorSaveReq);
        return "OK";
    }

    @GetMapping("/list")
    public List<AuthorListRes> authorList() {
        return authorService.findAll();
    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id) {
        authorService.delete(id);
        return "OK";
    }

    @GetMapping("/detail/{id}")
    public AuthorDetailRes authorDetail(@PathVariable Long id) {
        return authorService.authorDetail(id);
    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateReq authorUpdate) {
        authorService.update(id, authorUpdate);
        return "OK";
    }
}
