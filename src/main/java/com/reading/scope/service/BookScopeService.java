package com.reading.scope.service;

import com.reading.scope.dto.BookScopeRequestDTO;
import com.reading.scope.repository.BookScopeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookScopeService {
    private final BookScopeRepository bookScopeRepository;

    public void insertRegister(BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        bookScopeRepository.save(bookScopeRequestDTO.toEntity());
    }

    public Boolean existsBookScope(String isbn) {
        return bookScopeRepository.existsByIsbn(isbn);
    }

    public double MemberAverageRatingSearch(String isbn) throws Exception {

        List<Double> aveList = bookScopeRepository.findByIsbn(isbn);
        double ave = 0.0;

        for(double value : aveList) {
            ave += value;
        }

        ave = ave/aveList.size();

        return ave;
    }
}
