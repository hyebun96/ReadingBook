package com.reading.member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
public class MemberImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String img;

    @Builder
    public MemberImg(Long id, Member member, String img) {
        this.id = id;
        this.member = member;
        this.img = img;
    }

    public void updateImg(String img) {
        this.img = img;
    }
}
