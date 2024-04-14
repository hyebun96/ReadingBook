package com.reading.member.controller;

import com.reading.member.domain.Member;
import com.reading.member.domain.MemberImg;
import com.reading.member.dto.UploadProfileDTO;
import com.reading.member.repository.MemberImgRepository;
import com.reading.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class UploadProfileController {

    @Value("${com.reading.upload.path}")  // import시 springframework로 시작하는
    private String uploadPath;

    private final MemberRepository memberRepository;
    private final MemberImgRepository memberImgRepository;

    @PostMapping(value = "/uploadMemberImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadMemberImg(HttpSession httpSession, @ModelAttribute UploadProfileDTO uploadProfileDTO) throws  Exception{

        Member member = (Member) httpSession.getAttribute("member");
        Map<String, Object> map = new HashMap<>();

        if(uploadProfileDTO.getFiles() != null) {
            MultipartFile multipartFile = uploadProfileDTO.getFiles().get(0);

            String originalName = multipartFile.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();

            Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

            try {
                // 파일 업로드!
                multipartFile.transferTo(savePath);
                MemberImg memberImg= null;

                if(memberImgRepository.existsByMember(member)){
                    memberImg = memberImgRepository.findByMember(member).orElseThrow();
                    memberImg.updateImg(uuid+"_"+ originalName);
                } else {
                    memberImg = MemberImg.builder()
                            .member(member)
                            .img(uuid+"_"+ originalName)
                            .build();
                }
                MemberImg result = memberImgRepository.save(memberImg);

                map.put("memberImg", result);
                log.info(result.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}
