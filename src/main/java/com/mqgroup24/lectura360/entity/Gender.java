package com.mqgroup24.lectura360.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "genders", schema = "lectura360_db")
public class Gender {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @OneToMany(mappedBy = "gender")
    private Set<BookGender> bookGenders = new LinkedHashSet<>();

}