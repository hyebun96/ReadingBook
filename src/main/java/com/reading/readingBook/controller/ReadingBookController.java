package com.reading.readingBook.controller;

import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.service.BookShelfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/")
public class ReadingBookController {

    private final BookShelfService bookShelfService;

    @GetMapping
    public String index(Model model) throws IOException {

        List<BookShelfListResponseDTO> bookShelves = bookShelfService.findByMember_id(1L);

        if(bookShelves == null) {
            model.addAttribute("message", "내 책장에 도서가 등록되어 있지 않습니다. 책을 등록해보세요~!");
        } else {
            model.addAttribute("bookShelves", bookShelves);
        }

        return "index";
    }
}

