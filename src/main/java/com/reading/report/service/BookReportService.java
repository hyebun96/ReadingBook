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
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookReportService {

    private final BookReportRepository bookReportRepository;
    private final BookScopeRepository bookScopeRepository;

    public void insertReport(BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        BookReport bookReport = bookReportRepository.save(bookReportRequestDTO.toEntity());
        BookScope bookScope = bookScopeRequestDTO.toEntity();
        bookReport.addBookScope(bookScope);
        bookScope.addBookReport(bookReport);
        bookScopeRepository.save(bookScope);
    }

    public BookReportResponseDTO selectReportById(Long id) throws Exception {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        BookReportResponseDTO reportInfo = new BookReportResponseDTO();
        reportInfo.setId(bookReport.getId());
        reportInfo.setScope(bookReport.getBookScope().getScope());
        reportInfo.setReview(bookReport.getReview());
        reportInfo.setImpression(bookReport.getImpression());
        reportInfo.setLifeContent(bookReport.getLifeContent());
        return reportInfo;
    }

    public BookReportResponseDTO updateReport(Long id, BookReportRequestDTO bookRequestDTO, BookScopeRequestDTO scopeRequestDTO) throws Exception {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookReport.updateBookReport(bookRequestDTO);

        BookScope bookScope = bookReport.getBookScope();
        bookScope.updateBookScope(scopeRequestDTO);

        return new BookReportResponseDTO(bookReport);
    }

    public void deleteReport(Long id) throws Exception {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        bookScopeRepository.deleteByReportId(bookReport.getId());
        bookReportRepository.deleteById(id);
    }
}
