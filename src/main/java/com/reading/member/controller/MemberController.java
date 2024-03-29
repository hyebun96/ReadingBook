package com.reading.member.controller;

import com.reading.api.contorller.KakaoLoginAPI;
import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import com.reading.member.domain.Member;
import com.reading.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String token(@RequestParam String code,
                        HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes) throws IOException {

        KakaoTokenVO requestToken = kakaoLoginAPI.getToken(code);
        KakaoUserVO kakaoUserVO = kakaoLoginAPI.getUserInfo(requestToken);

        return register(requestToken, kakaoUserVO, httpServletRequest, redirectAttributes);
    }

    public String register(@RequestParam KakaoTokenVO requestToken,
                           @RequestParam KakaoUserVO kakaoUserVO,
                           HttpServletRequest httpServletRequest,
                           RedirectAttributes redirectAttributes) throws IOException {

        Member member = null;

        if(memberService.existsByMember(kakaoUserVO.getId())){
            member = memberService.findByMember(kakaoUserVO.getId());
            memberService.setToken(member, requestToken);
        } else {
            kakaoUserVO.setprofile((Map<String, Object>) kakaoUserVO.getKakao_account().get("profile"));
            member = memberService.save(requestToken, kakaoUserVO);
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("member", member);
        session.setMaxInactiveInterval(60 * 30);

        redirectAttributes.addFlashAttribute("member", member);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) throws IOException {

        Member member = (Member)session.getAttribute("member");

        kakaoLoginAPI.logout(member);
        kakaoLoginAPI.disconnect(member);

        // token 갈라
        Boolean result = memberService.logout(member);
        if(result){
            session.invalidate();
        }

        return "redirect:/";
    }


}
