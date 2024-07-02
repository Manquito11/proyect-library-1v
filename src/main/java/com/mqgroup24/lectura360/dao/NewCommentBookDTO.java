package com.mqgroup24.lectura360.dao;

import com.mqgroup24.lectura360.entity.Book;
import com.mqgroup24.lectura360.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewCommentBookDTO {

    private User user;

    private Book book;

    private String comment;
}
