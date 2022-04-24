package ru.otus.spring.hystrixapp.mongock.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;
import ru.otus.spring.hystrixapp.domain.Genre;
import ru.otus.spring.hystrixapp.repo.AuthorRepository;
import ru.otus.spring.hystrixapp.repo.BookRepository;
import ru.otus.spring.hystrixapp.repo.CommentRepository;
import ru.otus.spring.hystrixapp.repo.GenreRepository;


import java.util.ArrayList;
import java.util.List;


@ChangeLog
public class DatabaseChangelog {

        @ChangeSet(order = "001", id = "dropDb", author = "mikhail", runAlways = true)
        public void dropDb(MongoDatabase db) {
            db.drop();
        }

        @ChangeSet(order = "002", id = "insert1984", author = "mikhail")
        public void insert1984(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
            Author author = authorRepository.save(new Author("Orwell"));
            Genre genre = genreRepository.save(new Genre("Science Fiction"));
            List<Author> listAuthor = new ArrayList<>();
            List<Genre> listGenre = new ArrayList<>();
            listAuthor.add(author);
            listGenre.add(genre);
            Book book = repository.save(new Book("1984", listAuthor, listGenre));
            commentRepository.save(new Comment("perfect", book ));
}

        @ChangeSet(order = "003", id = "insertJaneEyre", author = "mikhail")
        public void insertJaneEyre(BookRepository repository, AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
            Author author = authorRepository.save(new Author("Bronte"));
            Genre genre = genreRepository.save(new Genre("Novel"));
            List<Author> listAuthor = new ArrayList<>();
            List<Genre> listGenre = new ArrayList<>();
            listAuthor.add(author);
            listGenre.add(genre);

            Book book = repository.save(new Book("Jane Eyre", listAuthor, listGenre));
            commentRepository.save(new Comment("not so bad",  book));
            commentRepository.save(new Comment("excellent work",  book));
        }

        @ChangeSet(order = "004", id = "insertAuth", author = "mikhail")
        public void insertAuthors(AuthorRepository authorRepository) {
            authorRepository.save(new Author("Pushkin"));
            authorRepository.save(new Author("Lermontov"));
            authorRepository.save(new Author("Paramonov"));
            authorRepository.save(new Author("Brown"));
        }

        @ChangeSet(order = "005", id = "insertGenres", author = "mikhail")
        public void insertGenres(GenreRepository genreRepository) {
            genreRepository.save(new Genre("Thriller"));
            genreRepository.save(new Genre("Fantasy"));
            genreRepository.save(new Genre("Horror"));
            genreRepository.save(new Genre("Poem"));
        }

}