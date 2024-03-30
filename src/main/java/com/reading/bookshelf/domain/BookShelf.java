package com.reading.bookshelf.domain;

import com.reading.book.domain.Book;
import com.reading.report.domain.BookReport;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"bookReport", "book"})
public class BookShelf {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long member_id;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bookReportId", referencedColumnName = "id")
    private BookReport bookReport;

    public void addBookReport(BookReport bookReport) {
        this.bookReport = bookReport;
    }
}
