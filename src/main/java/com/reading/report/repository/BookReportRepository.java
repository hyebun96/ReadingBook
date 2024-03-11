package com.reading.report.repository;

import com.reading.report.domain.BookReport;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<T, ID>
// JpaRepository -> 복잡한 JDBC(Java DataBase Connectivity) 코드를 작성하지 않아도 간단하게 DB와의 데이터 접근 작업을 처리할 수 있다
// T -> 엔티티 타입 (엔티티는 데이터를 저장하고 관리하는 것)
// ID -> 식별자 타입(PK)
public interface BookReportRepository extends JpaRepository<BookReport, Long> {
}
