package com.reading.scope.repository;

import com.reading.scope.domain.BookScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookScopeRepository extends JpaRepository<BookScope, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM BookScope b WHERE b.bookReport.id = :reportId")
    void deleteByReportId(Long reportId);

    boolean existsByIsbn(@Param("isbn") String isbn);

    @Query("SELECT b.scope FROM BookScope b WHERE  b.isbn = :isbn")
    List<Double> findByIsbn(@Param("isbn") String isbn);
}
