package com.mqgroup24.lectura360.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@NamedQuery(
        name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username=:username")
@NamedQuery(
        name = "User.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
@NamedQuery(
        name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email=:email")
@NamedQuery(
        name = "User.findByEmailAndPassword", query = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password")

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "users", schema = "lectura360_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Size(max = 20)
    @NotNull
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Size(max = 125)
    @NotNull
    @Column(name = "email", nullable = false, length = 125)
    private String email;

    @NotNull
    @Lob
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "user")
    private Set<CommentBook> commentBooks = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<ReactionComment> reactionComments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserBookFavorite> userBookFavorites = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserBookRating> userBookRatings = new LinkedHashSet<>();

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.USER.name();
        this.status = Status.ACTIVE.name();
    }

    static enum Status {
        ACTIVE, INACTIVE
    }

    static enum Role {
        ADMIN, USER
    }

}