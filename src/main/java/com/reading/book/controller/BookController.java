package com.reading.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {

    @GetMapping("/main")
    public String main(){
        return "index";
    }

}
