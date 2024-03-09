package com.reading.report.domain;

import com.reading.common.BaseEntity;
import com.reading.report.dto.BookReportRequestDTO;
import com.reading.scope.domain.BookScope;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BookReport extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int mybookId;

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

    @Builder
    public BookReport(int mybookId, String review, String impression, String lifeContent) {
        this.mybookId = mybookId;
        this.review = review;
        this.impression = impression;
        this.lifeContent = lifeContent;
    }

    public void addBookScope(BookScope bookScope) {
        this.bookScope = bookScope;
    }

    public void updateBookReport(BookReportRequestDTO requestDTO) {
        this.mybookId = requestDTO.getMybookId();
        this.review = requestDTO.getReview();
        this.impression = requestDTO.getImpression();
        this.lifeContent = requestDTO.getLifeContent();
    }
}
