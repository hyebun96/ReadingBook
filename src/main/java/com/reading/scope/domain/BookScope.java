package com.reading.scope.domain;

import com.reading.common.BaseEntity;
import com.reading.report.domain.BookReport;
import com.reading.scope.dto.BookScopeRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookScope extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    // 별점
    @Column
    private double scope;

    // 책 ISBN
    @Column(nullable = false)
    private String isbn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="reportId", referencedColumnName = "id")
    private BookReport bookReport;

    @Builder
    public BookScope(String memberId, double scope, String isbn, BookReport bookReport) {
        this.memberId = memberId;
        this.scope = scope;
        this.isbn = isbn;
        this.bookReport = bookReport;
    }

    public void addBookReport(BookReport bookReport) {
        this.bookReport = bookReport;
    }

    public void updateBookScope(BookScopeRequestDTO bookScopeResponseDTO) {
        this.scope = bookScopeResponseDTO.getScope();
    }
}
