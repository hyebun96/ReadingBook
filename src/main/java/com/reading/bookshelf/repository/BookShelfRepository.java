package com.reading.bookshelf.repository;

import com.reading.book.domain.Book;
import com.reading.bookshelf.domain.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    public boolean existsByBook(@Param("book_id") Book book);

    @Query("SELECT count(bs.id) FROM BookShelf bs WHERE bs.member_id = :member_id")
    public String existsByMember_id(@Param("member_id") Long member_id);

    @Query("SELECT new com.reading.bookshelf.domain.BookShelf(bs.id, bs.member_id, bs.book) FROM BookShelf bs WHERE bs.member_id = :member_id")
    public List<BookShelf> findByMember_id(Long member_id);

}