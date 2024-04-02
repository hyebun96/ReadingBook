package com.reading.member.repository;

import com.reading.member.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {

        // Given
        Member member = Member.builder()
                .access_token("access_token")
                .id_token("id_token")
                .nickname("hyebun")
                .profile_image_url("profile_image_url")
                .refresh_token("refresh_token")
                .token_type("token_type")
                .uuid("1")
                .build();


        // When
        Member result = memberRepository.save(member);

        // Then
       log.info(result);
    }

    @Test
    public void updateTest() {
        // Given
        //KakaoUserVO kakaoUserVO =
        Map<String, Object> profile = Map.of("nickname", "update nickname");
        Optional<Member> findMember = memberRepository.findByUuid(1L);
        Member member = findMember.orElseThrow();

        // When
        Member result = memberRepository.save(member);

        // Then
        assertEquals(result.getNickname(), "update nickname");
    }

    @Test
    public void deleteTest() {
        // Given
        Member member = memberRepository.findByUuid(1L).orElseThrow();

        // When & Then
        memberRepository.deleteById(member.getId());
    }

    @Test
    public void findByUuidTest() {
        // Given
        Long uuid = 1L;

        // When
        Member member1 = memberRepository.findByUuid(uuid).orElseThrow();;

        // Then
        assertEquals(member1.getAccess_token(), "access_token");
        assertEquals(member1.getId_token(), "id_token");
        assertEquals(member1.getRefresh_token(), "refresh_token");
        assertEquals(member1.getToken_type(), "token_type");
        assertEquals(member1.getNickname(), "hyebun");
        assertEquals(member1.getProfile_image_url(), "profile_image_url");
    }

    @Test
    public void existsByUuidTest() {
        // Given
        Long uuid = 1L;

        // When
        Boolean exist = memberRepository.existsByUuid(uuid);

        // Then
        assertEquals(exist, true);
        assertEquals(exist, false);
    }

}