package com.mqgroup24.lectura360.repository;

import com.mqgroup24.lectura360.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByTitle(@Param(("title")) String title);

}
