package com.reading.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Getter
@Table(name = "member")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Member {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String access_token;

    @Column(length= 10000, nullable = false)

    private String id_token;

    @Column(length = 500, nullable = false)
    private String refresh_token;

    @Column(length = 500, nullable = false)
    private String token_type;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String profile_image_url;

    public void addProfile(Map<String, Object> profile) {
        this.nickname = String.valueOf(profile.get("nickname"));
        this.profile_image_url = String.valueOf(profile.get("profile_image_url"));
    }
}
