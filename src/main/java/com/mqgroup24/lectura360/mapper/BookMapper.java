package com.mqgroup24.lectura360.mapper;

import com.mqgroup24.lectura360.dao.NewBookDTO;
import com.mqgroup24.lectura360.dao.BookCardDTO;
import com.mqgroup24.lectura360.dao.BookDetailsDTO;
import com.mqgroup24.lectura360.entity.Book;
import com.mqgroup24.lectura360.entity.BookGender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public static BookCardDTO convertToBookCardDTO(Book book) {
        return new BookCardDTO(
                book.getTitle(),
                book.getAuthor(),
                book.getUrlBookImage(),
                book.getRatingAverage()
        );
    }

    public static List<BookCardDTO> convertToBookCardDTO(List<Book> books) {
        return books.stream()
                .map(BookMapper::convertToBookCardDTO)
                .collect(Collectors.toList());
    }

    public static BookDetailsDTO convertToBookDetailsDTO(Book book) {
        return new BookDetailsDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getUrlBookImage(),
                book.getSynopsis(),
                book.getPublisher(),
                book.getReleaseYear(),
                book.getUrlBookPdf(),
                book.getRatingAverage(),
                book.getBookGenders().stream().map(BookGender::getGender).collect(Collectors.toSet()),
                book.getCommentBooks(),
                book.getCommentBooks().size()
        );
    }


}
