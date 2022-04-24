package ru.otus.spring.hystrixapp.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;
import ru.otus.spring.hystrixapp.domain.Genre;
import ru.otus.spring.hystrixapp.exception.NullDataException;
import ru.otus.spring.hystrixapp.repo.*;

import java.util.List;


@Service
public class LibServiceImpl implements LibService {

    private final BookRepository bookRepository;
    private final BookRepositoryCustomImpl bookRepositoryCustom;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;

    public LibServiceImpl(BookRepository bookRepository, BookRepositoryCustomImpl bookRepositoryCustom, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.bookRepositoryCustom = bookRepositoryCustom;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    @HystrixCommand(commandKey = "getBookByIdKey", fallbackMethod = "getDefaultValueBookById")
    public Book findById(String id) throws NullDataException {
        return bookRepository.findById(id).orElseThrow(NullDataException::new);
    }

    public Book getDefaultValueBookById(String id) throws NullDataException {

        return new Book("NULL",
                "NULL",
                List.of(new Author("NULL", "NULL")),
                List.of(new Genre("NULL", "NULL")));
    }

    @Override
    @HystrixCommand(commandKey = "getBooksKey", fallbackMethod = "getDefaultValueAllBooks")
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getDefaultValueAllBooks() throws NullDataException {

        return List.of(new Book("NULL",
                "NULL",
                List.of(new Author("NULL", "NULL")),
                List.of(new Genre("NULL", "NULL"))));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @HystrixCommand(commandKey = "getSaveBookKey", fallbackMethod = "getDefaultSaveBook")
    public Book saveBook(String title, String authorName, String genreName) {
        Author author;
        Genre genre;
        if (authorRepository.findAuthorByFullName(authorName).size() == 0) {
            author = new Author(authorName);
        } else {
            author = authorRepository.findAuthorByFullName(authorName).stream().findFirst().orElse(new Author());
        }
        authorRepository.save(author);
        if (genreRepository.findGenreByGenreName(genreName).size() == 0) {
            genre = new Genre(genreName);
        } else {
            genre = genreRepository.findGenreByGenreName(genreName).stream().findFirst().orElse(new Genre());
        }
        genreRepository.save(genre);
        return bookRepository.save(new Book(title, List.of(author), List.of(genre)));
    }

    public Book getDefaultSaveBook(String title, String authorName, String genreName) throws NullDataException {

        return new Book("NULL",
                "NULL",
                List.of(new Author("NULL", "NULL")),
                List.of(new Genre("NULL", "NULL")));
    }


    @Override
    @HystrixCommand(commandKey = "getUpdateBookKey", fallbackMethod = "getDefaultUpdateBook")
    public void updateBookTitle(String id, String title) {
        bookRepositoryCustom.updateBookTitleById(id, title);
    }

    public void getDefaultUpdateBook(String id, String title) throws NullDataException {
    }

    @Override
    @HystrixCommand(commandKey = "getDeleteBookKey", fallbackMethod = "getDefaultDeleteBook")
    public void deleteBookByIdCascade(String id) {
        bookRepositoryCustom.deleteBookCommentsCascade(id);
    }

    public void getDefaultDeleteBook(String id) throws NullDataException {
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public Comment saveComment(String commentText, String idBook) throws NullDataException {
        Book bookComment = bookRepository.findById(idBook).
                orElseThrow(NullDataException::new);
        return commentRepository.save(new Comment(commentText, bookComment));
    }

    @Override
    @HystrixCommand(commandKey = "getCommentsBookKey", fallbackMethod = "getDefaultCommentsBook")
    public List<Comment> findAllCommentsByBook(String idBook) throws NullDataException {
        return commentRepository.findCommentsByBook_Id(idBook);
    }

    public List<Comment> getDefaultCommentsBook(String idBook) throws NullDataException {

        return List.of(new Comment("NULL",
                new Book("NULL", "NULL",
                        List.of(new Author("NULL", "NULL")),
                        List.of(new Genre("NULL", "NULL")))));
    }


    @Override
    public Author findAuthorById(String id) throws NullDataException {
        return authorRepository.findById(id).
                orElseThrow(NullDataException::new);
    }

    @Override
    public List<Author> findAuthorByName(String name) throws NullDataException {
        return authorRepository.findAuthorByFullName(name);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

}
