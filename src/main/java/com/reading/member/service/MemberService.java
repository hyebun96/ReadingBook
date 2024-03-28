package com.reading.member.service;

import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import com.reading.member.domain.Member;
import com.reading.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    public Boolean existsByMember(Long uuid) {
        return memberRepository.existsByUuid(uuid);
    }

    public Member findByMember(Long uuid) {
        return memberRepository.findByUuid(uuid).orElseThrow();
    }

    public Member save(KakaoTokenVO requestToken, KakaoUserVO kakaoUserVO) {

        Member member = modelMapper.map(requestToken, Member.class);
        member.addProfile(kakaoUserVO);

        return memberRepository.save(member);
    }

    public Member setToken(Member member,KakaoTokenVO requestToken){
        member.setToken(requestToken);

        return memberRepository.save(member);
    }

    public Boolean logout(Member member) {

        member.resetToken();
        Member result = memberRepository.save(member);

        if(result.getAccess_token().equals("")
                && result.getRefresh_token().equals("")
                && result.getId_token().equals("")) {
            return true;
        }

        return false;
    }
}
