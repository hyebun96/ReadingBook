//package com.reading.member.service;
//
//import com.reading.member.domain.Member;
//import com.reading.member.repository.MemberRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class UploadProfileServiceTest {
//
//    @Autowired
//    private UploadProfileService uploadProfileService;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void deleteFileTest() {
//        // Given
//        Member member = memberRepository.findById(24L).orElseThrow();
//
//        // When
//        uploadProfileService.deleteFile(member);
//
//        // Then
//    }
//
//}