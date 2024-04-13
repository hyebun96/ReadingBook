package com.reading.member.controller;

import com.reading.member.domain.Member;
import com.reading.member.dto.UploadProfileDTO;
import com.reading.member.dto.UploadResultDTO;
import com.reading.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
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

    @PostMapping(value = "/uploadMemberImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map<String, Object> uploadMemberImg(HttpSession httpSession, @ModelAttribute UploadProfileDTO uploadProfileDTO) throws  Exception{

        Member member = (Member) httpSession.getAttribute("member");
        log.info("file --> " + uploadProfileDTO.toString());
        log.info("member --> " + member);

        if(uploadProfileDTO.getFiles() != null) {
            MultipartFile multipartFile = uploadProfileDTO.getFiles().get(0);

            String originalName = multipartFile.getOriginalFilename();
            log.info(originalName);

            String uuid = UUID.randomUUID().toString();

            Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

            boolean image = false;


            try {

                // 파일 업로드!
                multipartFile.transferTo(savePath);

            } catch (IOException e) {
                e.printStackTrace();
            }

            // member img 변경
//            member.updateProfile_image_url();

        }

        Map<String, Object> map = new HashMap<>();
        map.put("img"," igm");

        return map;
    }

}
