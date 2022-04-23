package ru.otus.spring.hystrixapp.service;





import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;
import ru.otus.spring.hystrixapp.domain.Genre;
import ru.otus.spring.hystrixapp.exception.NullDataException;

import java.util.List;

public interface LibService {

    Author findAuthorById(String id) throws NullDataException;
    List<Author> findAuthorByName(String name) throws NullDataException;
    List<Author> findAllAuthors();
    List<Genre> findAllGenres();


    Book findById (String id) throws NullDataException;
    List<Book> findAllBooks();
    Book saveBook(String title, String authorName, String genreName);
    Book save(Book book);
    void updateBookTitle(String id, String title);
    void deleteBookByIdCascade(String id);
    List<Book> findBookByTitle (String title);

    Comment saveComment(String commentText, String idBook) throws NullDataException;
    List<Comment> findAllCommentsByBook(String idBook) throws NullDataException;

}
