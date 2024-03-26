package com.reading.member.service;

import com.reading.api.domain.KakaoTokenVO;
import com.reading.member.domain.Member;
import com.reading.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    public void save(KakaoTokenVO requestToken, Map<String, Object> profile) {

        Member member = modelMapper.map(requestToken, Member.class);
        member.addProfile(profile);

        log.info(requestToken.toString());
        log.info(profile.toString());
        log.info(member.toString());

        Member member2 = memberRepository.save(member);
    }
}
