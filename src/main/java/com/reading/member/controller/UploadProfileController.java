package com.reading.member.controller;

import com.reading.member.domain.Member;
import com.reading.member.domain.MemberImg;
import com.reading.member.dto.UploadProfileDTO;
import com.reading.member.service.MemberService;
import com.reading.member.service.UploadProfileService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("profile")
@Log4j2
@RequiredArgsConstructor
public class UploadProfileController {

    private final MemberService memberService;
    private final UploadProfileService uploadProfileService;

    @PostMapping(value = "/uploadMemberImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadMemberImg(
            HttpSession httpSession,
            @ModelAttribute UploadProfileDTO uploadProfileDTO) throws  Exception{

        Member member = (Member) httpSession.getAttribute("member");
        Map<String, Object> map = new HashMap<>();

        if(uploadProfileDTO.getFiles() != null) {

            if(uploadProfileService.existsByMember(member)){
                uploadProfileService.fileDelete(member);
            }

            MultipartFile multipartFile = uploadProfileDTO.getFiles().get(0);
            String saveFileName = uploadProfileService.fileUpload(multipartFile, uploadProfileDTO);
            MemberImg memberImg = uploadProfileService.save(member, saveFileName);

            map.put("img", true);
            map.put("imgsrc", "/common/profile/" + memberImg.getImg());

            member.updateImg("/common/profile/" + memberImg.getImg());
            httpSession.setAttribute("member", member);
            log.info(memberImg.toString());
            log.info(map.get("imgsrc"));

        }
        return map;
    }

    @PostMapping(value = "/return")
    public Map<String, Object> uploadMemberImg(
            HttpSession httpSession) throws Exception{

        Map<String, Object> map = new HashMap<>();

        Member member = (Member) httpSession.getAttribute("member");

        if(uploadProfileService.existsByMember(member)){
            uploadProfileService.fileDelete(member);
        }

        MemberImg memberImg = uploadProfileService.findByMemberImg(member);
        uploadProfileService.deleteMemberImg(memberImg);



        try {
            Long uuid = Long.valueOf(member.getUuid());
            httpSession.setAttribute("member", memberService.findByMember(uuid));

            map.put("result", true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }
}
