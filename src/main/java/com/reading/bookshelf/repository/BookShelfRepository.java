package com.reading.bookshelf.repository;

import com.reading.bookshelf.domain.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {

    @Query("SELECT b.id FROM BookShelf b WHERE b.member_id = :member_id AND b.isbn = :isbn")
    public Long findByIsbnAndMember_id(@Param("member_id") Long member_id, @Param("isbn") String isbn );


}
