package com.reading.member.repository;

import com.reading.member.domain.Member;
import com.reading.member.domain.MemberImg;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberImgRepositoryTest {

    @Autowired
    private MemberImgRepository memberImgRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByMemberTest() {
        // Given
        Member member = memberRepository.findById(24L).orElseThrow();

        // When
        MemberImg result = memberImgRepository.findByMember(member).orElseThrow();
        Boolean exist = memberImgRepository.existsByMember(member);
        String getImg = memberImgRepository.getMemberImgByImg(member);

        // Then
        log.info(result.toString());
        log.info(exist);
        log.info(getImg);
    }

}