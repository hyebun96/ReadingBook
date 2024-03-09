package com.reading.scope.repository;

import com.reading.scope.domain.BookScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookScopeRepository extends JpaRepository<BookScope, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM BookScope b WHERE b.bookReport.id = :id")
    void deleteByReportId(@Param("id") Long reportId);
}
