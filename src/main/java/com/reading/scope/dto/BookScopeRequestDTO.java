package com.reading.scope.dto;

import com.reading.scope.domain.BookScope;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookScopeRequestDTO {
    private String memberId;
    private double scope;
    private String isbn;

    public BookScope toEntity() {
        return BookScope.builder()
                .memberId("hyehwa")
                .scope(scope)
                .isbn("9791192625553")
                .build();
    }
}
