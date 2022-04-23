package ru.otus.spring.hystrixapp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String commentText;
    @DBRef
    private Book book;

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    public Comment(String id, String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentText='" + commentText + '\'' +
                ""
                +
                ", book="
                + book +
                '}';
    }
}
