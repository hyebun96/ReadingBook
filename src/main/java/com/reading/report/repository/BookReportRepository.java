package com.reading.report.repository;

import com.reading.report.domain.BookReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReportRepository extends JpaRepository<BookReport, Long> {
}
