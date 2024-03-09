package com.reading.report.controller;

import com.reading.report.domain.BookReport;
import com.reading.report.dto.BookReportRequestDTO;
import com.reading.report.dto.BookReportResponseDTO;
import com.reading.report.service.BookReportService;
import com.reading.scope.dto.BookScopeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/report")
public class BookReportController {

    private final BookReportService bookReportService;

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("regRequestDTO", new BookReportResponseDTO());
        return "report/reportRegister";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("regRequestDTO") BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        bookReportService.insertReport(bookReportRequestDTO, bookScopeRequestDTO);
        return "report/reportRegister";
    }

    @GetMapping("/view/{id}")
    public String selectReportById(@PathVariable("id") Long id, Model model) throws Exception {
        model.addAttribute("report", bookReportService.selectReportById(id));
        return "report/reportView";
    }

    @PostMapping("/modify/{id}")
    public String modifyReportById(@PathVariable("id") Long id, BookReport bookReport, Model model) throws Exception {
        model.addAttribute("reportInfo", bookReportService.selectReportById(id));
        return "report/reportEdit";
    }

    @PostMapping("/update/{id}")
    public String patchReportById(@ModelAttribute("regRequestDTO") @PathVariable("id") Long id, BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        bookReportService.updateReport(id, bookReportRequestDTO, bookScopeRequestDTO);
        return "report/reportRegister";
    }

    @PostMapping("/delete/{id}")
    public String deleteReport(@PathVariable Long id) throws Exception {
        bookReportService.deleteReport(id);
        return "book/main";
    }
}



