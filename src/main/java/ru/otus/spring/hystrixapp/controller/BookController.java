package ru.otus.spring.hystrixapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.hystrixapp.domain.Author;
import ru.otus.spring.hystrixapp.domain.Book;
import ru.otus.spring.hystrixapp.domain.Comment;
import ru.otus.spring.hystrixapp.domain.Genre;
import ru.otus.spring.hystrixapp.dto.AuthorDto;
import ru.otus.spring.hystrixapp.dto.BookDto;
import ru.otus.spring.hystrixapp.dto.CommentDto;
import ru.otus.spring.hystrixapp.dto.GenreDto;
import ru.otus.spring.hystrixapp.exception.NullDataException;
import ru.otus.spring.hystrixapp.service.LibService;


import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BookController {

    private final LibService libService;

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = libService.findAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) throws NullDataException {
        Book book = libService.findById(id);
        List<Comment> comments = libService.findAllCommentsByBook(id);
        model.addAttribute("book", book);
        model.addAttribute("author", book.getAuthor().get(0));
        model.addAttribute("genre", book.getGenre().get(0));
        model.addAttribute("comments", comments);
        return "edit";
    }

    @Validated
    @PostMapping("/edit")
    public String updateBook (@Valid @ModelAttribute("book") BookDto book,
                              BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }

        libService.updateBookTitle(book.getId(), book.getTitle());
        return "redirect:/";
    }

    @GetMapping("/savebook")
    public String editPageSave(Model model) throws NullDataException {
        BookDto book = new BookDto();
        AuthorDto author = new AuthorDto();
        GenreDto genre = new GenreDto();
        List<Author> authors = libService.findAllAuthors();
        List<Genre> genres = libService.findAllGenres();
        model.addAttribute("book", book);
        model.addAttribute("author", author);
        model.addAttribute("genre", genre);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "savebook";
    }

    @Validated
    @PostMapping("/savebook")
    public String saveBook(@ModelAttribute("book") BookDto book,
                           @ModelAttribute("author") AuthorDto author,
                           @ModelAttribute("genre") GenreDto genre,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "savebook";
        }
        libService.saveBook(book.getTitle(), author.getFullName(), genre.getGenreName());
        return "redirect:/";
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public String deletePage (@RequestParam("id") String id) {
        libService.deleteBookByIdCascade(id);
        return "delete"; //"delete"
    }

    @GetMapping("/savecomment")
    public String editPageSaveComment(@RequestParam("id") String id, Model model) throws NullDataException {
        Book book = libService.findById(id);
        Comment comment = libService.findAllCommentsByBook(id).get(0);
        model.addAttribute("bookss", book);
        model.addAttribute("comment", comment);
        return "savecomment";
    }

    @Validated
    @PostMapping("/savecomment")
    public String saveCommentNew(
                           @ModelAttribute("bookss") BookDto book,
                           @ModelAttribute("comment") CommentDto comment,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "savecomment";
        }

        libService.saveComment(comment.getCommentText(), comment.getBook().getId());
        return "redirect:/";
    }

}
