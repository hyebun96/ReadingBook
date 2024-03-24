package com.reading.member.controller;

import com.reading.api.contorller.KakaoLoginAPI;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final KakaoLoginAPI kakaoLoginAPI;

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("requestParam", kakaoLoginAPI.getKey());

        return "member/login";
    }

    @GetMapping("/kakao")
    public String token(@RequestParam String code, Model model) throws IOException {

        log.info(code);

        String access_token = kakaoLoginAPI.getToken(code);

        return "index";
    }


}
