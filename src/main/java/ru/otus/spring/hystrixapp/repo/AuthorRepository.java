package ru.otus.spring.hystrixapp.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.hystrixapp.domain.Author;


import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author,String> {

    List<Author> findAll();

    @Override
    Optional<Author> findById(String s);

    @Query("{ 'fullName': ?0 }")
    List<Author> findAuthorByFullName(String fullName);
}
