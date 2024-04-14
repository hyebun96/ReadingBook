package com.reading.member.repository;

import com.reading.member.domain.Member;
import com.reading.member.domain.MemberImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberImgRepository extends JpaRepository<MemberImg, Long> {

    @Query("SELECT m FROM MemberImg m WHERE m.member = :member")
    Optional<MemberImg> findByMember(@Param("member") Member member);

    Boolean existsByMember(@Param("member") Member member);


    @Query("SELECT m.img FROM MemberImg m WHERE m.member = :member")
    String getMemberImgByImg(@Param("member") Member member);

}
