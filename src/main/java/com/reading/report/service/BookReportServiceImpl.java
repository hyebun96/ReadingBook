package com.reading.report.service;

import com.reading.report.domain.BookReport;
import com.reading.report.dto.BookReportDTO;
import com.reading.report.repository.BookReportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BookReportServiceImpl implements BookReportService {

    private final ModelMapper modelMapper;
    private final BookReportRepository bookReportRepository;

    @Override
    public Long register(BookReportDTO bookReportDTO) {

        BookReport bookReport = modelMapper.map(bookReportDTO, BookReport.class);

        Long id = bookReportRepository.save(bookReport).getId();

        return id;
    }
}
