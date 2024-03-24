package com.reading.api.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reading.api.domain.KakaoTokenVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

@Component
@Log4j2
public class KakaoLoginAPI implements APIInterface<KakaoTokenVO>{

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.kakao.redirect_uri}")
    private String REDIRECT_URL;

    public Map<String, String> getKey() {

        Map<String, String> requestParam = Map.of(
                                            "CLIENT_ID", CLIENT_ID,
                                            "REDIRECT_URL", REDIRECT_URL
        );

        return requestParam;
    }

    public String getToken(String code) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_url", REDIRECT_URL)
                .queryParam("response_type", "code")
                .queryParam("grant_type", "authorization_code")
                .queryParam("code", "code")
                .encode()
                .build()
                .toUri();

        KakaoTokenVO resultVO = connect(uri, "POST");

        return resultVO.getAccess_token();
    }

    @Override
    public KakaoTokenVO connect(URI uri, String method) throws IOException {

        RequestEntity<Void> req = null;

        if(method.equals("GET")){
            req = RequestEntity.<Void>get(uri).build();
        } else if(method.equals("POST")) {
            req = RequestEntity.<Void>post(uri).build();
        }

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        ObjectMapper om = new ObjectMapper();
        KakaoTokenVO vo = null;

        try {
            vo = om.readValue(resp.getBody(), KakaoTokenVO.class);
        } catch (ClassCastException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;
    }
}
