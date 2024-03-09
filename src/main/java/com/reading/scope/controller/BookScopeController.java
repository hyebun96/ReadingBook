package com.reading.scope.controller;

import com.reading.report.dto.BookReportResponseDTO;
import com.reading.scope.dto.BookScopeRequestDTO;
import com.reading.scope.service.BookScopeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/scope")
public class BookScopeController {

    private final BookScopeService bookScopeService;

    @GetMapping("/register")
    public String getRegister(@RequestParam("isbn") String isbn, BookScopeRequestDTO bookScopeRequestDTO) throws Exception {
        bookScopeService.insertRegister(bookScopeRequestDTO);
        return "scope/register";
    }
}
