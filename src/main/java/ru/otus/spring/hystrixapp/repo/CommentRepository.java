package ru.otus.spring.hystrixapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.spring.hystrixapp.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment,String> {

    List<Comment> findAll();

    @Override
    Optional<Comment> findById(String s);

    List<Comment> findCommentsByBook_Id(String id);

}
