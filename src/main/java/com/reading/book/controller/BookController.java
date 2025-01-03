package com.reading.book.controller;

import com.reading.api.service.APIService;
import com.reading.book.dto.BookDetailResponseDTO;
import com.reading.book.dto.PageRequestDTO;
import com.reading.book.dto.PageResponseDTO;
import com.reading.book.service.BookService;
import com.reading.scope.service.BookScopeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
@Log4j2
public class BookController {

    private final APIService apiService;
    private final BookService bookService;

    private final BookScopeService bookScopeService;

    @GetMapping("search")
    public String search(@RequestParam(name = "title", required=true, defaultValue = "") String title, Model model) throws IOException {

        if(title.equals("")){
            model.addAttribute("result", false);
            model.addAttribute("default", true);
            model.addAttribute("searchBookList", null);
            model.addAttribute("pageResponseDTO", new PageResponseDTO());
        } else {
            Map<String, Object> map = apiService.allSearchByTitleInNaver(title, new PageRequestDTO());

            if((boolean) map.get("exist")){
                model.addAttribute("result", true);
                model.addAttribute("default", false);
                model.addAttribute("searchBookList", map.get("searchBookList"));
                model.addAttribute("pageResponseDTO", map.get("pageResponseDTO"));
            } else {
                model.addAttribute("result", false);
                model.addAttribute("default", false);
                model.addAttribute("searchBookList", null);
                model.addAttribute("pageResponseDTO", new PageResponseDTO());
            }
        }

        model.addAttribute("title", title);

        return "book/search";
    }

    @GetMapping("detail/{isbn}")
    public String searchDetail(@PathVariable("isbn") String isbn, Model model) throws Exception {

        BookDetailResponseDTO bookDetailResponseDTO = bookService.searchDetail(isbn);
        if(bookScopeService.existsBookScope(isbn)){
            double aveRating = bookScopeService.MemberAverageRatingSearch(isbn);
            model.addAttribute("aveRating", aveRating);
            log.info("---->" + aveRating);
        }


        model.addAttribute("book", bookDetailResponseDTO);

        return "book/detail";
    }

    @PostMapping("/save/{isbn}")
    public String save(@PathVariable("isbn") String isbn) throws IOException {

        String redirectIsbn = bookService.save(isbn);

        return "redirect:/bookshelf/save/" + redirectIsbn;
    }

}
