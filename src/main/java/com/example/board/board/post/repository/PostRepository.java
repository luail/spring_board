package com.example.board.board.post.repository;

import com.example.board.board.author.domain.Author;
import com.example.board.board.post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(Author author);
    Page<Post> findAll(Pageable pageable);

    Page<Post> findAllByAppointment(Pageable pageable, String appointment);

//    jpql을 사용한 일반 join
    @Query("select p from Post p inner join p.author")
    List<Post> findAllJoin();

//    jpql을 사용한 fetch join
//    순수쿼리: select * from post p left join author a on a.id=p.author_id;
    @Query("select p from Post p inner join fetch p.author")
    List<Post> findAllFetchJoin();
}
