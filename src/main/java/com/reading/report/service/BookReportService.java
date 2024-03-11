package com.reading.report.service;

import com.reading.report.domain.BookReport;
import com.reading.report.dto.BookReportRequestDTO;
import com.reading.report.dto.BookReportResponseDTO;
import com.reading.report.repository.BookReportRepository;
import com.reading.scope.domain.BookScope;
import com.reading.scope.dto.BookScopeRequestDTO;
import com.reading.scope.repository.BookScopeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookReportService {

    private final BookReportRepository bookReportRepository;
    private final BookScopeRepository bookScopeRepository;

    private final ModelMapper modelMapper;

    public void insertReport(BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO) {
        BookReport bookReport = bookReportRepository.save(bookReportRequestDTO.toEntity());
        BookScope bookScope = bookScopeRequestDTO.toEntity();
        bookReport.addBookScope(bookScope);
        bookScope.addBookReport(bookReport);
        bookScopeRepository.save(bookScope);
    }

    public BookReportResponseDTO selectReportById(Long id) {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        BookReportResponseDTO bookReportResponseDTO = modelMapper.map(bookReport, BookReportResponseDTO.class);
        bookReportResponseDTO.setScope(bookReport.getBookScope().getScope());

        return bookReportResponseDTO;
    }

    public BookReportResponseDTO updateReport(Long id, BookReportRequestDTO bookRequestDTO, BookScopeRequestDTO scopeRequestDTO) {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookReport.updateBookReport(bookRequestDTO);

        BookScope bookScope = bookReport.getBookScope();
        bookScope.updateBookScope(scopeRequestDTO);

        return new BookReportResponseDTO(bookReport);
    }

    public void deleteReport(Long id) {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookScopeRepository.deleteByReportId(bookReport.getId());
        bookReportRepository.deleteById(id);
    }
}
