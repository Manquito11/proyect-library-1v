package com.mqgroup24.lectura360.controller;

import com.mqgroup24.lectura360.dao.BookCardDTO;
import com.mqgroup24.lectura360.dao.NewCommentBookFormDTO;
import com.mqgroup24.lectura360.service.BookService;
import com.mqgroup24.lectura360.service.CommentBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class CommentBookController {

    @Autowired
    private CommentBookService commentBookService;

    @Autowired
    private BookService bookService;

    @PostMapping("/comment")
    public String comment(@Valid @ModelAttribute NewCommentBookFormDTO newCommentBookFormDTO, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
        BookCardDTO bookCardFound = bookService.findBookById(newCommentBookFormDTO.getBookId());
        commentBookService.createCommentBook(newCommentBookFormDTO);

        String encodedTitle = bookCardFound.getTitle().replace(" ", "%20");

        redirectAttributes.addAttribute("title", encodedTitle);

        System.out.println(encodedTitle);

        return "redirect:/book/{title}";
    }

}
