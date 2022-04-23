package ru.otus.spring.hystrixapp.dto;


import ru.otus.spring.hystrixapp.domain.Author;

public class AuthorDto {

    private String id;
    private String fullName;

    public AuthorDto() {
    }

    public AuthorDto(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public AuthorDto(String fullName) {
        this.fullName = fullName;
    }

    public Author toDomainObject(){
        return new Author(id, fullName);
    }

    public static AuthorDto fromDomainObject(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }
}
