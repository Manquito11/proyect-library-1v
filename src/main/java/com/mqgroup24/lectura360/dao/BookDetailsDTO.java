package com.mqgroup24.lectura360.dao;

import com.mqgroup24.lectura360.entity.BookGender;
import com.mqgroup24.lectura360.entity.CommentBook;
import com.mqgroup24.lectura360.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class BookDetailsDTO {

    private Long id;

    private String title;

    private String author;

    private String urlBookImage;

    private String synopsis;

    private String publisher;

    private Integer releaseYear;

    private String urlBookPdf;

    private BigDecimal ratingAverage;

    private Set<Gender> bookGenders;

    private Set<CommentBook> commentBooks;

    private Integer numberOfComments;

}
