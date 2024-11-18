package com.reading.bookshelf.domain;

import com.reading.book.domain.Book;
import com.reading.member.domain.Member;
import com.reading.report.domain.BookReport;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor
@ToString(exclude = {"bookReport", "book"})
public class BookShelf {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne()
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="bookReportId", referencedColumnName = "id")
    private BookReport bookReport;

    @Builder
    public BookShelf(Long id, Member member, Book book, BookReport bookReport) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.bookReport = bookReport;
    }

    public void addBookReport(BookReport bookReport) {
        this.bookReport = bookReport;
    }
}
