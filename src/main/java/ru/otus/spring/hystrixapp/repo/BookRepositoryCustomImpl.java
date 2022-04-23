package ru.otus.spring.hystrixapp.repo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;

@Component
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Data
    private class ArraySizeProjection {
        private int size;
    }

    private final MongoTemplate mongoTemplate;


    @Override
    public void deleteBookCommentsCascade(String idBook) {

        mongoTemplate.remove(new Query(Criteria.where("id").is(idBook)), Book.class); //удаляю книгу
        mongoTemplate.remove(new Query(Criteria.where("book.id").is(idBook)), Comment.class); //удаляю комменты

    }

    public void updateBookTitleById(String idBook, String title) {
        Query query =  Query.query(Criteria.where("id").is(idBook));
        mongoTemplate.updateFirst(query,Update.update("title", title),Book.class);
    }




}
