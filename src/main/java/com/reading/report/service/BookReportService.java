package com.reading.report.service;

import com.reading.report.dto.BookReportDTO;

public interface BookReportService {

    // 등록
    Long register(BookReportDTO bookReportDTO);
}