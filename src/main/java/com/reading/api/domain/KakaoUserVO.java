package com.reading.api.domain;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@ToString
public class KakaoUserVO {

    private Long id;                    // 회원 고유 아이디 member에서는 uuid로 처리할것
    private Date connected_at;
    private Map<String, Object> properties;
    private Map<String, Object> kakao_account;
    private Map<String, Object> profile;

    public void setprofile(Map<String , Object> profile) {
        this.profile = profile;
    }
}
