package com.reading.report.controller;

import com.reading.report.dto.BookReportRequestDTO;
import com.reading.report.dto.BookReportResponseDTO;
import com.reading.report.service.BookReportService;
import com.reading.scope.dto.BookScopeRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("report")
@Log4j2
public class BookReportController {

    private final BookReportService bookReportService;

    @GetMapping("form")
    public String getForm(Model model, @RequestParam("id") Long id) {
        model.addAttribute("bookShelfId", id);
        model.addAttribute("bookInfo", bookReportService.bookInfo(id));
        return "report/reportForm";
    }

    @GetMapping("register/{id}")
    public String getRegister(Model model, @PathVariable("id") Long id) {
        model.addAttribute("bookShelfId", id);
        model.addAttribute("bookInfo", bookReportService.bookInfo(id));
        model.addAttribute("regRequestDTO", new BookReportResponseDTO());
        return "report/reportRegister";
    }

    @PostMapping("/register")
    // .html에서 th:object를 적용하기 위해 해당 오브젝트 정보를 넘겨 주어야 한다.
    // 객체를 전달하지 않을 경우 예외가 발생한다.
    // @ModelAttribute -> Model에 지정한 객체를 자동으로 넣어준다
    public String postRegister(@ModelAttribute("regRequestDTO") BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO, Long id) throws IOException {
        Long reportId = bookReportService.insertReport(bookReportRequestDTO, bookScopeRequestDTO, id);
        return "redirect:/report/view/" + reportId;
    }

    @GetMapping("view/{id}")
    // @PathVariable -> 경로 변수를 표시하기 위한 메서드에서 매개변수에 사용
    public String selectReportById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookInfo", bookReportService.bookView(id));
        model.addAttribute("report", bookReportService.selectReportById(id));
        return "report/reportView";
    }

    @GetMapping("modify/{id}")
    public String modifyReportById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookInfo", bookReportService.bookView(id));
        model.addAttribute("reportInfo", bookReportService.selectReportById(id));
        return "report/reportEdit";
    }

    @PostMapping("/update/{id}")
    public String patchReportById(@ModelAttribute("regRequestDTO") @PathVariable("id") Long id, BookReportRequestDTO bookReportRequestDTO, BookScopeRequestDTO bookScopeRequestDTO) {
        bookReportService.updateReport(id, bookReportRequestDTO, bookScopeRequestDTO);
        return "redirect:/report/view/" + id;
    }

    @PostMapping("/delete/{id}")
    public String deleteReport(@PathVariable("id") Long id) {
        bookReportService.deleteReport(id);
        return "redirect:/bookshelf/list";
    }
}
