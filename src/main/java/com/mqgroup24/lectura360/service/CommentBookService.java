package com.mqgroup24.lectura360.service;

import com.mqgroup24.lectura360.dao.NewCommentBookDTO;
import com.mqgroup24.lectura360.dao.NewCommentBookFormDTO;
import com.mqgroup24.lectura360.entity.Book;
import com.mqgroup24.lectura360.entity.User;
import com.mqgroup24.lectura360.mapper.CommentBookMapper;
import com.mqgroup24.lectura360.repository.BookRepository;
import com.mqgroup24.lectura360.repository.CommentBookRepository;
import com.mqgroup24.lectura360.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentBookService {

    @Autowired
    private CommentBookRepository commentBookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public void createCommentBook(NewCommentBookFormDTO newCommentBookFormDTO) {
        User userFound = userRepository.findById(newCommentBookFormDTO.getUserId()).orElse(null);

        Book bookFound = bookRepository.findById(newCommentBookFormDTO.getBookId()).orElse(null);

        String comment = newCommentBookFormDTO.getComment();

        if (userFound != null && bookFound != null) {
            NewCommentBookDTO newCommentBookDTO = new NewCommentBookDTO(
                    userFound,
                    bookFound,
                    comment
            );
            commentBookRepository.save(CommentBookMapper.toConvertCommentBook(newCommentBookDTO));
        }
    }

}
