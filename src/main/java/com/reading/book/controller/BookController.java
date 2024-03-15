package com.reading.book.controller;

import com.reading.book.domain.BookDetailResponseDTO;
import com.reading.book.domain.BookListResponseDTO;
import com.reading.book.service.BookService;
import com.reading.bookshelf.controller.BookShelfController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Log4j2
public class BookController {

    private final BookService bookService;
    private final BookShelfController bookShelfController;
    private final ModelMapper modelMapper;


    @GetMapping("/search")
    public String search(@RequestParam("title") String title, Model model) throws IOException {

        List<BookListResponseDTO> searchBookList  = bookService.allSearchByIsbnInNaver(title);

        model.addAttribute("searchBookList", searchBookList);
        model.addAttribute("title", title);

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
