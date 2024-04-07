package com.reading.bookshelf.controller;

import com.reading.bookshelf.domain.BookShelfListResponseDTO;
import com.reading.bookshelf.service.BookShelfService;
import com.reading.member.controller.MemberController;
import com.reading.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/bookshelf")
@RequiredArgsConstructor
public class BookShelfController {

    private final BookShelfService bookShelfService;

    private final MemberController memberController;

    @GetMapping("/save/{isbn}")
    public String getSave(@PathVariable("isbn") String isbn, RedirectAttributes redirectAttributes) throws IOException {

        Member member = memberController.getSessionMember();

        if(member == null){
            return "/book/" + isbn;
        }

       String url = postSave(member, isbn, redirectAttributes);

       return url;
    }


    public String postSave(@RequestParam("member") Member member,
                           @RequestParam("isbn") String isbn,
                           RedirectAttributes redirectAttributes) throws IOException {

        Boolean existBookShlf = bookShelfService.save(isbn, member);

        if(existBookShlf){
            // modal 창에 값 보내기
            redirectAttributes.addFlashAttribute("message", "이미 내 서재에 등록된 도서 입니다.");
        } else {
            redirectAttributes.addFlashAttribute("message", "내 서재에 도서가 등록이 되었습니다.");
        }
        redirectAttributes.addFlashAttribute("existMessage", true);

        return "redirect:/book/detail/" + isbn;
    }

    @GetMapping("/list")
    public String list(Model model) throws IOException {

        Member member = memberController.getSessionMember();

        List<BookShelfListResponseDTO> bookShelfList = bookShelfService.findByMember(member);
        model.addAttribute("bookShelfList", bookShelfList);
        model.addAttribute("member", member);

        return "/bookshelf/list";
    }
}
