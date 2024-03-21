package com.reading.bookshelf.repository;

import com.reading.book.domain.Book;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    boolean existsByBook(@Param("book_id") Book book);

    @Query("SELECT count(bs.id) FROM BookShelf bs WHERE bs.member_id = :member_id")
    String existsByMember_id(@Param("member_id") Long member_id);

    @Query("SELECT new com.reading.bookshelf.domain.BookShelfListResponseDTO(" +
            "bs.id, " +
            "bs.member_id, " +
            "bs.book.isbn, " +
            "bs.book.title, " +
            "bs.book.image, " +
            "bs.bookReport.id) " +
            "FROM BookShelf bs WHERE bs.member_id = :member_id")
    List<BookShelfListResponseDTO> findByMember_id(Long member_id);

}