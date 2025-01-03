package com.reading.member.controller;

import com.reading.api.contorller.KakaoLoginAPI;
import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import com.reading.member.domain.Member;
import com.reading.member.dto.MemberProfileDTO;
import com.reading.member.repository.MemberImgRepository;
import com.reading.member.service.MemberService;
import com.reading.member.service.UploadProfileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "https://port-0-intobooks-1lx8gjwrw.sel5.cloudtype.app/")
@Controller
@RequestMapping("member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final KakaoLoginAPI kakaoLoginAPI;
    private final MemberService memberService;

    private final HttpSession session;

    private final MemberImgRepository memberImgRepository;

    private final UploadProfileService uploadProfileService;


    public Member getSessionMember(){
        return (Member) session.getAttribute("member");
    }

    @GetMapping("login")
    public String login(Model model){

        model.addAttribute("CLIENT_ID", kakaoLoginAPI.getCLIENT_ID());
        model.addAttribute("REDIRECT_URI", kakaoLoginAPI.getREDIRECT_URI());

        return "member/login";
    }

    @GetMapping("kakao/callback")
    public String token(@RequestParam("code") String code,
                        HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes) throws IOException {

        KakaoTokenVO requestToken = kakaoLoginAPI.getToken(code);
        KakaoUserVO kakaoUserVO = kakaoLoginAPI.getUserInfo(requestToken.getAccess_token());

        return register(requestToken, kakaoUserVO, httpServletRequest, redirectAttributes);
    }

    public String register(@RequestParam("requestToken") KakaoTokenVO requestToken,
                           @RequestParam("kakaoUserVO") KakaoUserVO kakaoUserVO,
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

        if(uploadProfileService.existsByMember(member)){
            member.updateImg("/common/profile/" + uploadProfileService.findMemberImg(member));
        }

        session.setAttribute("member", member);
        session.setMaxInactiveInterval(60 * 30);

        redirectAttributes.addFlashAttribute("member", member);

        return "redirect:/";
    }

    @GetMapping("logout")
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

    @GetMapping("profile")
    public String profile(HttpSession session, Model model) throws IOException {

        Long uuid = Long.valueOf(((Member) session.getAttribute("member")).getUuid());
        Member member = memberService.findByMember(uuid);
        Boolean existMemberImg = memberImgRepository.existsByMember(member);
        MemberProfileDTO memberProfileDTO = null;

        if(existMemberImg) {
            memberProfileDTO = MemberProfileDTO.builder()
                                             .profile_img_url("/common/profile/" + memberImgRepository.getMemberImgByImg(member))
                                             .nickname(member.getNickname())
                                             .uuid(member.getUuid())
                                             .build();

            model.addAttribute("setting_kakao_img", false);
        } else {
            memberProfileDTO = MemberProfileDTO.builder()
                                             .profile_img_url(member.getProfile_image_url())
                                             .nickname(member.getNickname())
                                             .uuid(member.getUuid())
                                             .build();

            model.addAttribute("setting_kakao_img", true);
        }

        log.info(memberProfileDTO.toString());

        model.addAttribute("memberProfileDTO", memberProfileDTO);

        return "member/profile";
    }

}
