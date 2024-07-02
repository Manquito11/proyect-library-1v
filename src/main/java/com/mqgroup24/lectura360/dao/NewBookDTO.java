package com.mqgroup24.lectura360.dao;

import com.mqgroup24.lectura360.entity.BookGender;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class NewBookDTO {

    private String title;

    private String author;

    private String synopsis;

    private String publisher;

    private String isbn;

    private Integer releaseYear;

    private byte[] bookImage;

    private byte[] bookPdf;

    private Set<BookGender> bookGenders;

}
