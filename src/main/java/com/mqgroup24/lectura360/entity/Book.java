package com.mqgroup24.lectura360.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(
                name = "Book.findByTitle",
                query = "SELECT b FROM Book b WHERE b.title=:title"
        ),
})

@Getter
@Setter
@Entity
@Table(name = "books", schema = "lectura360_db")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 75)
    @NotNull
    @Column(name = "title", nullable = false, length = 75)
    private String title;

    @Size(max = 75)
    @NotNull
    @Column(name = "author", nullable = false, length = 75)
    private String author;

    @Size(max = 245)
    @NotNull
    @Column(name = "synopsis", nullable = false, length = 245)
    private String synopsis;

    @Size(max = 50)
    @NotNull
    @Column(name = "publisher", nullable = false, length = 50)
    private String publisher;

    @Size(max = 17)
    @NotNull
    @Column(name = "isbn", nullable = false, length = 17)
    private String isbn;

    @NotNull
    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Size(max = 125)
    @NotNull
    @Column(name = "url_book_image", nullable = false, length = 125)
    private String urlBookImage;

    @Size(max = 125)
    @NotNull
    @Column(name = "url_book_pdf", nullable = false, length = 125)
    private String urlBookPdf;

    @NotNull
    @Column(name = "rating_average", nullable = false, precision = 2, scale = 1)
    private BigDecimal ratingAverage;

    @OneToMany(mappedBy = "book")
    private Set<BookGender> bookGenders = new LinkedHashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<CommentBook> commentBooks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<UserBookFavorite> userBookFavorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "book")
    private Set<UserBookRating> userBookRatings = new LinkedHashSet<>();

}