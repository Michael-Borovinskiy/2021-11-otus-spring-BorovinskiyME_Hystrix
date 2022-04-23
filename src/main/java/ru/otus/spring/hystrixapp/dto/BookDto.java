package ru.otus.spring.hystrixapp.dto;



import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Genre;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class BookDto {

    private String id;

    @NotBlank()
    @Size(max = 40)
    private String title;

    private List<Author> author;
    private List<Genre> genre;

    public BookDto() {
    }

    public BookDto(String id, String title, List<Author> author, List<Genre> genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public Book toDomainObject(){
        return new Book(id, title, author, genre);
    }

    public static BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre());
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }
}
