package com.reading.scope.service;

import com.reading.scope.domain.BookScope;
import com.reading.scope.dto.BookScopeRequestDTO;
import com.reading.scope.repository.BookScopeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookScopeService {
    private final BookScopeRepository bookScopeRepository;

    public void insertRegister(BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        bookScopeRepository.save(bookScopeRequestDTO.toEntity());
    }
}
