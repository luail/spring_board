package com.example.board.board.post.service;

import com.example.board.board.author.domain.Author;
import com.example.board.board.author.repository.AuthorRepository;
import com.example.board.board.post.domain.Post;
import com.example.board.board.post.dtos.PostDetailRes;
import com.example.board.board.post.dtos.PostListRes;
import com.example.board.board.post.dtos.PostSaveReq;
import com.example.board.board.post.dtos.PostUpdateReq;
import com.example.board.board.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }


    public void save(PostSaveReq dto) throws EntityNotFoundException {
        Author author = authorRepository.findByEmail(dto.getEmail()).orElseThrow(()->new EntityNotFoundException("author is not found"));
        postRepository.save(dto.toEntity(author));
    }

    public List<PostListRes> findAll() {
        return postRepository.findAll().stream().map(p->p.postListResFromEntity()).collect(Collectors.toList());
    }

    public Page<PostListRes> findAllPaging(Pageable pageable) {
        Page<Post> pagePosts = postRepository.findAll(pageable);
        return pagePosts.map(p->p.postListResFromEntity());
    }

    public PostDetailRes findById(Long id) {
        return postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("post is not found")).detailResFromEntity();
    }

    public void update(Long id, PostUpdateReq dto) {
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("post is not found"));
        post.update(dto);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
