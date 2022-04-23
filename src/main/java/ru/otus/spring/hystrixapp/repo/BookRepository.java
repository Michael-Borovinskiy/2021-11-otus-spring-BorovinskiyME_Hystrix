package ru.otus.spring.hystrixapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.hystrixapp.domain.Book;


import java.util.List;
import java.util.Optional;


public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

    List<Book> findAll();

    @Override
    Optional<Book> findById(String s);

    @Query("{ 'title': ?0 }")
    List<Book> findBookByTitle (String title);

}
