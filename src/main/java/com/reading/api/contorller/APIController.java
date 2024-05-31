package com.reading.api.contorller;

import com.reading.api.service.APIService;
import com.reading.book.dto.PageRequestDTO;
import com.reading.book.service.BookService;
import com.reading.bookshelf.service.BookShelfService;
import com.reading.member.controller.MemberController;
import com.reading.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

    private final APIService apiService;
    private final BookService bookService;
    private final MemberController memberController;
    private final BookShelfService bookShelfService;

    @GetMapping("/search")
    public Map<String, Object> moreSearch(@RequestParam("title") String title, @ModelAttribute PageRequestDTO pageRequestDTO) throws IOException {

        Map<String, Object> map = apiService.allSearchByTitleInNaver(title, pageRequestDTO);

        return map;
    }

    @GetMapping("/save/{isbn}")
    public Map<String, Object> save(@PathVariable("isbn") String isbn) throws IOException {

        Map<String, Object> map = new HashMap<>();
        Member member = memberController.getSessionMember();

        bookService.save(isbn);

        return bookShelfService.save(isbn, member);
    }

}
