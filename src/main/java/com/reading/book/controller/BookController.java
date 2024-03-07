package com.reading.book.controller;

import com.reading.api.NaverBookAPI;
import com.reading.api.domain.Book;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.domain.BookListResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/book")
@RequiredArgsConstructor
@Log4j2
public class BookController {

    private final NaverBookAPI naverBookAPI;

    private final ModelMapper modelMapper;

    @GetMapping("/search")
    public String search(@RequestParam("title") String title, Model model) throws IOException {

        NaverResultVO naverResultVO = naverBookAPI.searchBookAll(title);

        List<Book> items = naverResultVO.getItems();
        List<BookListResponseDTO> searchBookList = items.stream()
                .map(book -> modelMapper.map(book, BookListResponseDTO.class))
                .toList();

        model.addAttribute("searchBookList", searchBookList);
        model.addAttribute("title", title);

        return "/book/search";
    }

}
