package com.reading.book.repository;

import com.reading.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    boolean existsByIsbn(@Param("isbn") String isbn);

    Optional<Book> findByIsbn(@Param("isbn") String isbn);
}
