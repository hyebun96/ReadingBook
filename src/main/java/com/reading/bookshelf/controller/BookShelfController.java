package com.reading.bookshelf.controller;

import com.reading.bookshelf.service.BookShelfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
@Log4j2
public class BookShelfController {

    private final BookShelfService bookShelfService;

    @GetMapping("/save/{isbn}")
    public String getSave(@PathVariable("isbn") String isbn, RedirectAttributes redirectAttributes) throws IOException {

       String url = postSave(isbn, redirectAttributes);

       return url;
    }


    public String postSave(@PathVariable("isbn") String isbn, RedirectAttributes redirectAttributes) throws IOException {

        Boolean existBookShlf = bookShelfService.save(isbn);

        if(existBookShlf){
            // modal 창에 값 보내기
            redirectAttributes.addFlashAttribute("message", "이미 내 서재에 등록된 도서 입니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "내 서재에 도서가 등록이 되었습니다.");
        }
        redirectAttributes.addFlashAttribute("existMessage", true);

        return "redirect:/book/detail/" + isbn;
    }
}
