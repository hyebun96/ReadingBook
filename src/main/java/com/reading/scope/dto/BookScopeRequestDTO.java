package com.reading.scope.dto;

import com.reading.scope.domain.BookScope;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class BookScopeRequestDTO {
    private Long memberId;
    private double scope;
    private String isbn;

    public BookScope toEntity() {
        return BookScope.builder()
                .memberId(memberId)
                .scope(scope)
                .isbn(isbn)
                .build();
    }
}
