package com.reading.readingBook.controller;

import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.service.BookShelfService;
import com.reading.member.domain.Member;
import com.reading.readingBook.repository.ReadingBookRepositorySupport;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Log4j2
public class ReadingBookController {

    private final BookShelfService bookShelfService;
    private final ReadingBookRepositorySupport readingBookRepositorySupport;

    @GetMapping
    public String index(HttpSession session, Model model) throws Exception {

        Member member = (Member) session.getAttribute("member");
        List<BookShelfListResponseDTO> bookShelves = bookShelfService.findByMember(member);

        if(bookShelves == null) {
            model.addAttribute("message", "내 책장에 도서가 등록되어 있지 않습니다. " +
                    "책을 등록해보세요🐻‍❄️");
        } else {
            model.addAttribute("bookShelves", bookShelves);
        }

        model.addAttribute("bookChartData", readingBookRepositorySupport.findAllByMonthChartData(member.getId()));

        return "index";
    }
}

