package com.reading.report.service;

import com.reading.book.domain.Book;
import com.reading.book.dto.BookDetailResponseDTO;
import com.reading.bookshelf.domain.BookShelf;
import com.reading.bookshelf.repository.BookShelfRepository;
import com.reading.bookshelf.service.BookShelfService;
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

import java.io.IOException;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookReportService {

    private final BookReportRepository bookReportRepository;
    private final BookScopeRepository bookScopeRepository;
    private final BookShelfRepository bookShelfRepository;
    private final BookShelfService bookShelfService;

    private final ModelMapper modelMapper;

    public Long insertReport(BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO, Long id) throws IOException {
        BookReport bookReport = bookReportRepository.save(bookReportRequestDTO.toEntity());
        BookScope bookScope = bookScopeRequestDTO.toEntity();

        BookShelf bookShelf = bookShelfService.saveBookReport(bookReport, id);

        bookReport.addBookScope(bookScope);
        bookShelf.addBookReport(bookReport);

        bookScope.addBookReport(bookReport);
        bookScopeRepository.save(bookScope);

        return bookReport.getId();
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
        bookShelfRepository.deleteById(bookReport.getId());
    }

    public BookDetailResponseDTO bookInfo(Long id) {
        BookShelf bookShelf = bookShelfRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        Book bookInfo = bookShelf.getBook();
        return modelMapper.map(bookInfo, BookDetailResponseDTO.class);
    }

    public BookDetailResponseDTO bookView(Long id) {
        BookReport bookReport = bookReportRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        BookShelf bookShelf = bookReport.getBookShelf();
        Book bookInfo = bookShelf.getBook();

        return modelMapper.map(bookInfo, BookDetailResponseDTO.class);
    }

}
