package com.reading.member.domain;

import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Table(name = "member")
@Entity
@NoArgsConstructor
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

    @Column(nullable = false, unique = true)
    private String uuid;

    @Builder
    public Member(String access_token, String id_token, String refresh_token, String token_type, String nickname, String profile_image_url, String uuid) {
        this.access_token = access_token;
        this.id_token = id_token;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.nickname = nickname;
        this.profile_image_url = profile_image_url;
        this.uuid = uuid;
    }

    public void addProfile(KakaoUserVO kakaoUserVO) {
        this.nickname = String.valueOf(kakaoUserVO.getProfile().get("nickname"));
        this.profile_image_url = String.valueOf(kakaoUserVO.getProfile().get("profile_image_url"));
        this.uuid = String.valueOf(kakaoUserVO.getId());
    }

    public void updateImg(String profile_image_url){
        this.profile_image_url = profile_image_url;
    }

    public void setToken(KakaoTokenVO kakaoTokenVO) {
        this.access_token = kakaoTokenVO.getAccess_token();
        this.id_token = kakaoTokenVO.getId_token();
        this.refresh_token = kakaoTokenVO.getRefresh_token();
    }

    public void resetToken() {
        this.access_token = "";
        this.id_token = "";
        this.refresh_token = "";
    }
}
