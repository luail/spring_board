package com.example.board.board.post.controller;

import com.example.board.board.post.dtos.PostDetailRes;
import com.example.board.board.post.dtos.PostListRes;
import com.example.board.board.post.dtos.PostSaveReq;
import com.example.board.board.post.dtos.PostUpdateReq;
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

    @GetMapping("/detail/{id}")
    public PostDetailRes postDetail(@PathVariable Long id) {
        return postService.findById(id);
    }

    @PostMapping("/update/{id}")
    public String postUpdate(@PathVariable Long id, @ModelAttribute PostUpdateReq dto) {
        postService.update(id, dto);
        return "OK";
    }

    @GetMapping("/delete/{id}")
    public String postDelete(@PathVariable Long id) {
        postService.delete(id);
        return "OK";
    }
}
