package ru.otus.spring.hystrixapp.repo;

public interface BookRepositoryCustom {

    void deleteBookCommentsCascade(String idBook);
    void updateBookTitleById(String idBook, String title);
}
