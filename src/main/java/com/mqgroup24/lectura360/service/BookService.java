package com.mqgroup24.lectura360.service;

import com.mqgroup24.lectura360.dao.BookCardDTO;
import com.mqgroup24.lectura360.dao.BookDetailsDTO;
import com.mqgroup24.lectura360.entity.Book;
import com.mqgroup24.lectura360.mapper.BookMapper;
import com.mqgroup24.lectura360.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) { this.bookRepository = bookRepository; }

    public List<BookCardDTO> findAllBooks() {
        return BookMapper.convertToBookCardDTO(bookRepository.findAll());
    }

    public BookDetailsDTO findBookByTitle(String title) {
        title = title.replace("%20", " ");
        Book bookFound = bookRepository.findByTitle(title);

        if (bookFound != null) {
            return BookMapper.convertToBookDetailsDTO(bookFound);
        }

        return null;
    }

    public BookCardDTO findBookById(Long id) {
        return BookMapper.convertToBookCardDTO(bookRepository.findById(id).orElse(null));
    }

}
