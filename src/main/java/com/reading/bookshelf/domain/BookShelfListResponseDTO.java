package com.reading.bookshelf.domain;

import com.reading.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BookShelfListResponseDTO {

    private Long id;
    private Member member;
    private String isbn;
    private String title;
    private String image;
    private Long reportId;

    @Builder
    public BookShelfListResponseDTO(Long id, Member member, String isbn, String title, String image, Long reportId) {
        this.id = id;
        this.member = member;
        this.isbn = isbn;
        this.title = title;
        this.image = image;
        this.reportId = reportId;
    }
}
