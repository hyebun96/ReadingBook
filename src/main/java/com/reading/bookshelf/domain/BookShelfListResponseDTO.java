package com.reading.bookshelf.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookShelfListResponseDTO {

    private Long id;
    private Long member_id;
    private String isbn;
    private String title;
    private String image;
    private Long reportId;
}
