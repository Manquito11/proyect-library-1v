package com.mqgroup24.lectura360.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BookCardDTO {

    private String title;

    private String author;

    private String urlBookImage;

    private BigDecimal ratingAverage;

}
