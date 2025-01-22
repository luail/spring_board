package com.example.board.board.author.controller;

import com.example.board.board.author.dtos.AuthorDetailRes;
import com.example.board.board.author.dtos.AuthorListRes;
import com.example.board.board.author.dtos.AuthorSaveReq;
import com.example.board.board.author.dtos.AuthorUpdateReq;
import com.example.board.board.author.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/create")
    public String authorCreate() {
        return "author/author_create";
    }

    @PostMapping("/create")
    public String authorCreate(@ModelAttribute @Valid AuthorSaveReq authorSaveReq) {
        authorService.save(authorSaveReq);
        return "redirect:/";
    }

    @GetMapping("/list")
    public String authorList(Model model) {
        List<AuthorListRes> authorListRes = authorService.findAll();
        model.addAttribute("authorList", authorListRes);
        return "author/author_list";
    }

    @GetMapping("/delete/{id}")
    public String authorDelete(@PathVariable Long id) {
        authorService.delete(id);
        return "OK";
    }

    @GetMapping("/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model) {
        AuthorDetailRes dto = authorService.authorDetail(id);
        model.addAttribute("author", dto);
        return "author/author_detail";
    }

    @PostMapping("/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateReq authorUpdate) {
        authorService.update(id, authorUpdate);
        return "OK";
    }
}
