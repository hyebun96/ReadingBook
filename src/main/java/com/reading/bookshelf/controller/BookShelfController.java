package com.reading.bookshelf.controller;

import com.reading.bookshelf.service.BookShelfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/bookShelf")
@RequiredArgsConstructor
@Log4j2
public class BookShelfController {

    private final BookShelfService bookShelfService;

    @PostMapping("/save/{isbn}")
    public String postSave(@PathVariable("isbn") String isbn, RedirectAttributes redirectAttributes) throws IOException {

        Boolean existBookShlf = bookShelfService.save(isbn);

        if(existBookShlf){
            // modal 창에 값 보내기
           redirectAttributes.addFlashAttribute("message", "이미 내 서재에 등록된 도서 입니다.");
        }

        return "redirect:/book/detail/" + isbn;
    }
}
