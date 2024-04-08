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
import java.util.Map;

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

        Map<String, Object> map = bookShelfService.save(isbn, member);

        redirectAttributes.addFlashAttribute("message", map.get("message"));
        redirectAttributes.addFlashAttribute("existMessage", map.get("existMessage"));

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
