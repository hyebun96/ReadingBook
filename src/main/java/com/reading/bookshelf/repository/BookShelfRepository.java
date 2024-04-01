package com.reading.bookshelf.repository;

import com.reading.book.domain.Book;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {


    boolean existsByBookAndMember(@Param("book_id") Book book, Member member);

    @Query("SELECT count(bs.id) FROM BookShelf bs WHERE bs.member = :member")
    String existsByMember(@Param("member") Member member);

    @Query("SELECT new com.reading.bookshelf.domain.BookShelfListResponseDTO(" +
            "bs.id, " +
            "bs.member, " +
            "bs.book.isbn, " +
            "bs.book.title, " +
            "bs.book.image, " +
            "bs.bookReport.id) " +
            "FROM BookShelf bs WHERE bs.member = :member")
    List<BookShelfListResponseDTO> findByMember(Member member);

}