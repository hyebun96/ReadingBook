package com.reading.member.controller;

import com.reading.api.contorller.KakaoLoginAPI;
import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import com.reading.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final KakaoLoginAPI kakaoLoginAPI;
    private final MemberService memberService;

    @GetMapping("/login")
    public String login(Model model){

        model.addAttribute("CLIENT_ID", kakaoLoginAPI.getCLIENT_ID());
        model.addAttribute("REDIRECT_URI", kakaoLoginAPI.getREDIRECT_URI());

        return "member/login";
    }

    @GetMapping("/kakao/callback")
    public String token(@RequestParam String code) throws IOException {

        KakaoTokenVO requestToken = kakaoLoginAPI.getToken(code);
        KakaoUserVO kakaoUserVO = kakaoLoginAPI.getUserInfo(requestToken);
        kakaoUserVO.setprofile((Map<String, Object>) kakaoUserVO.getKakao_account().get("profile"));

        return register(requestToken, kakaoUserVO.getProfile());
    }

    public String register(@RequestParam KakaoTokenVO requestToken,
                           @RequestParam Map<String, Object> profile) throws IOException {

        memberService.save(requestToken, profile);

        return "index";
    }


}
