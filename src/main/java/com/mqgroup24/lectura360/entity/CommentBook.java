package com.mqgroup24.lectura360.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "comment_books", schema = "lectura360_db")
public class CommentBook {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    @Lob
    @Column(name = "comment", nullable = false)
    private String comment;

    @ColumnDefault("0")
    @Column(name = "like_count")
    private Integer likeCount;

    @ColumnDefault("0")
    @Column(name = "dislike_count")
    private Integer dislikeCount;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "commentBooks")
    private Set<ReactionComment> reactionComments = new LinkedHashSet<>();

    public CommentBook (User user, Book book, String comment) {
        this.user = user;
        this.book = book;
        this.comment = comment;
        this.likeCount = 0;
        this.dislikeCount = 0;
        this.createdAt = new Timestamp(System.currentTimeMillis()).toInstant();
    }

    public CommentBook() {

    }

    public String getFormattedCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(createdAt, ZoneId.systemDefault());
        return localDateTime.format(formatter);
    }

}