package com.reading.scope.dto;

import com.reading.scope.domain.BookScope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookScopeResponseDTO {
    private double scope;
    private String isbn;

    public BookScopeResponseDTO(BookScope bookScope) {
        this.scope = bookScope.getScope();
        this.isbn = bookScope.getIsbn();
    }
}
