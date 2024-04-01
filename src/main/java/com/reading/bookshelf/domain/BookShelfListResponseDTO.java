package com.reading.bookshelf.domain;

import com.reading.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookShelfListResponseDTO {

    private Long id;
    private Member member;
    private String isbn;
    private String title;
    private String image;
    private Long reportId;
}
