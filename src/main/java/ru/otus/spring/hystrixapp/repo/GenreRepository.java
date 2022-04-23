package ru.otus.spring.hystrixapp.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.spring.hystrixapp.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre,String> {

    List<Genre> findAll();

    @Override
    Optional<Genre> findById(String s);

    @Query("{ 'genreName': ?0 }")
    List<Genre> findGenreByGenreName (String genreName);

}
