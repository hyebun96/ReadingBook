package com.reading.report.domain;

import com.reading.bookshelf.domain.BookShelf;
import com.reading.common.BaseEntity;
import com.reading.report.dto.BookReportRequestDTO;
import com.reading.scope.domain.BookScope;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 줄거리
    @Column
    private String review;

    // 느낀점
    @Column
    private String impression;

    // 삶의 적용
    @Column
    private String lifeContent;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "bookReport", cascade = CascadeType.ALL)
    private BookScope bookScope;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookshelfId", referencedColumnName = "id")
    private BookShelf bookShelf;


    @Builder
    // 불필요한 생성자 제거
    // null 체크 가능
    public BookReport(String review, String impression, String lifeContent) {
        this.review = review;
        this.impression = impression;
        this.lifeContent = lifeContent;
    }

    public void addBookScope(BookScope bookScope) {
        this.bookScope = bookScope;
    }

    public void addBookShelf(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
    }

    public void updateBookReport(BookReportRequestDTO requestDTO) {
        this.review = requestDTO.getReview();
        this.impression = requestDTO.getImpression();
        this.lifeContent = requestDTO.getLifeContent();
    }
}
