package com.reading.member.repository;

import com.reading.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.uuid =:uuid")
    Optional<Member> findByUuid(Long uuid);

    @Query("select count(m) > 0 from Member m where m.uuid =:uuid")
    Boolean existsByUuid(Long uuid);


}
