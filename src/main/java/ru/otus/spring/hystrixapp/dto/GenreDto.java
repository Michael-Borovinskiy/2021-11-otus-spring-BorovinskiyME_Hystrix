package ru.otus.spring.hystrixapp.dto;


import ru.otus.spring.hystrixapp.domain.Genre;

public class GenreDto {

    private String id;
    private String genreName;

    public GenreDto(String id, String genreName) {
        this.id = id;
        this.genreName = genreName;
    }

    public GenreDto(String genreName) {
        this.genreName = genreName;
    }

    public GenreDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public Genre toDomainObject(){
        return new Genre(id, genreName);
    }

    public static GenreDto fromDomainObject(Genre genre) {
        return new GenreDto(genre.getId(), genre.getGenreName());
    }
}
