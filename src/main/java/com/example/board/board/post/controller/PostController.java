package com.example.board.board.post.controller;

import com.example.board.board.post.dtos.PostListRes;
import com.example.board.board.post.dtos.PostSaveReq;
import com.example.board.board.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute PostSaveReq dto) {
        postService.save(dto);
        return "OK";
    }

    @GetMapping("/list")
    public List<PostListRes> postList() {
        return postService.findAll();
    }
}
