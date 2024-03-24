package com.reading.api.domain;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class KakaoTokenVO implements Serializable {

    private String access_token;
    private String token_type;
    private String id_token;
    private int expires_in;
    private String refresh_token;
    private int refresh_token_expires_in;
    private String scope;
}
