package com.example.board.board.post.repository;

import com.example.board.board.author.domain.Author;
import com.example.board.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Author author);
    Page<Post> findAll(Pageable pageable);
}
