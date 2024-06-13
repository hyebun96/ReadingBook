package com.reading.intobooks;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("intobooks")
public class IntoBooksController {

    @GetMapping("info")
    public String info() {
        return "intobooks/info";
    }
}
