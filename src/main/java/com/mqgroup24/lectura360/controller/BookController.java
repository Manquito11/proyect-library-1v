package com.mqgroup24.lectura360.controller;

import com.mqgroup24.lectura360.dao.BookDetailsDTO;
import com.mqgroup24.lectura360.entity.CommentBook;
import com.mqgroup24.lectura360.service.BookService;
import com.mqgroup24.lectura360.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "index";
    }

    @GetMapping("/search/")
    public String getSearchBooks(
            @RequestParam(name = "q", required = false) String title,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "author", required = false) String author,
            Model model) {
        return "search-books";
    }

    @GetMapping("/book/{title}")
    public String getBookDetails(@PathVariable(required = true) String title, Model model) {
        BookDetailsDTO bookDetailsDTO = bookService.findBookByTitle(title);
        Set<CommentBook> commentBooks = bookDetailsDTO.getCommentBooks();

        List<CommentBook> sortedCommentBooks = commentBooks.stream()
                .sorted(Comparator.comparing(CommentBook::getCreatedAt).reversed())
                .toList();

        model.addAttribute("bookDetails", bookDetailsDTO);
        model.addAttribute("commentBooks", sortedCommentBooks);
        return "book-details";
    }

}
