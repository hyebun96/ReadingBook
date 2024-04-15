package com.reading.member.service;

import com.reading.member.domain.Member;
import com.reading.member.domain.MemberImg;
import com.reading.member.dto.UploadProfileDTO;
import com.reading.member.repository.MemberImgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class UploadProfileService {

    @Value("${com.reading.upload.path}")  // import시 springframework로 시작하는
    private String uploadPath;

    private final MemberImgRepository memberImgRepository;

    public boolean existsByMember(Member member) {
        return memberImgRepository.existsByMember(member);
    }

    public String findMemberImg(Member member){
        return memberImgRepository.getMemberImgByImg(member);
    }

    public String fileUpload(MultipartFile multipartFile, UploadProfileDTO uploadProfileDTO) {

        String originalName = multipartFile.getOriginalFilename();

        String uuid = UUID.randomUUID().toString();

        Path savePath = Paths.get(uploadPath, uuid+"_"+ originalName);

        try {
            // 파일 업로드!
            multipartFile.transferTo(savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uuid + "_" + originalName;
    }

    public MemberImg save(Member member, String saveFileName) {

        MemberImg memberImg= null;

        if(existsByMember(member)){
            memberImg = memberImgRepository.findByMember(member).orElseThrow();
            memberImg.updateImg(saveFileName);
        } else {
            memberImg = MemberImg.builder()
                    .member(member)
                    .img(saveFileName)
                    .build();
        }

        return memberImgRepository.save(memberImg);
    }

    public void fileDelete(Member member) {
        String saveFileName = memberImgRepository.getMemberImgByImg(member);

        try {
            Path filePath = Paths.get(uploadPath  + "/" + saveFileName);
            Files.delete(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteMemberImg(MemberImg memberImg) {
        memberImgRepository.delete(memberImg);
    }

    public MemberImg findByMemberImg(Member member){
        return memberImgRepository.findByMember(member).orElseThrow();
    }
}
