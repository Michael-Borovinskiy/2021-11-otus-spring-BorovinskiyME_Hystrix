package ru.otus.spring.hystrixapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;
import ru.otus.spring.hystrixapp.domain.Genre;
import ru.otus.spring.hystrixapp.exception.NullDataException;
import ru.otus.spring.hystrixapp.repo.*;


import java.util.List;

@RequiredArgsConstructor
@Service
public class LibServiceImpl implements LibService {

    private final BookRepository bookRepository;
    private final BookRepositoryCustomImpl bookRepositoryCustom;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;


    @Override
    public Book findById(String id) throws NullDataException {
        return bookRepository.findById(id).orElseThrow(NullDataException::new);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
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

    @Override
    public void updateBookTitle(String id, String title) {
        bookRepositoryCustom.updateBookTitleById(id, title);
    }

    @Override
    public void deleteBookByIdCascade(String id) {
        bookRepositoryCustom.deleteBookCommentsCascade(id);
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
    public List<Comment> findAllCommentsByBook(String idBook) throws NullDataException {
        return commentRepository.findCommentsByBook_Id(idBook);
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
