package ru.otus.spring.hystrixapp.dto;


import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;

public class CommentDto {

    private String id;
    private String commentText;
    private Book book;

    public CommentDto() {
    }

    public CommentDto(String id, String commentText, Book book) {
        this.id = id;
        this.commentText = commentText;
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

    public Book getBook() { //List<Book>
        return book;
    }

    public void setBook(Book book) { //List<Book>
        this.book = book;
    }

    public Comment toDomainObject(){
        return new Comment(id, commentText, book);
    }

    public static CommentDto fromDomainObject(Comment comment) {
        return new CommentDto(comment.getId(), comment.getCommentText(),comment.getBook());
    }
}
