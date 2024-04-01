package com.reading.book.controller;

import com.reading.api.service.APIService;
import com.reading.book.dto.BookDetailResponseDTO;
import com.reading.book.dto.PageRequestDTO;
import com.reading.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final APIService apiService;
    private final BookService bookService;

    @GetMapping("/search")
    public String search(@RequestParam("title") String title, Model model) throws IOException {

        Map<String, Object> map = apiService.allSearchByTitleInNaver(title, new PageRequestDTO());

        model.addAttribute("message", map.get("message"));
        model.addAttribute("title", title);
        model.addAttribute("searchBookList", map.get("searchBookList"));
        model.addAttribute("pageResponseDTO", map.get("pageResponseDTO"));

        return "/book/search";
    }

    @GetMapping("/detail/{isbn}")
    public String searchDetail(@PathVariable("isbn") String isbn, Model model) throws IOException {

        BookDetailResponseDTO bookDetailResponseDTO = bookService.searchDetail(isbn);

        model.addAttribute("book", bookDetailResponseDTO);

        return "/book/detail";
    }

    @PostMapping("/save/{isbn}")
    public String save(@PathVariable("isbn") String isbn) throws IOException {

        String redirectIsbn = bookService.save(isbn);

        return "redirect:/bookshelf/save/" + redirectIsbn;
    }

}
