package com.reading.api.contorller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reading.api.domain.KakaoTokenVO;
import com.reading.api.domain.KakaoUserVO;
import com.reading.member.domain.Member;
import lombok.Getter;
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
    @Getter
    private String CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect_uri}")
    @Getter
    private String REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String CLIENT_SECRET;

    public KakaoTokenVO getToken(String code) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://kauth.kakao.com")
                .path("/oauth/token")
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", CLIENT_ID)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("code", code)
                .encode()
                .build()
                .toUri();

        KakaoTokenVO responseToken = connect(postMethodRequestEntity(uri));

        return responseToken;
    }

    public KakaoUserVO getUserInfo(String access_token) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v2/user/me")
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.<Void>get(uri)
                .header("Authorization", "Bearer " + access_token)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        ObjectMapper om = new ObjectMapper();
        KakaoUserVO vo = null;

        try {
            vo = om.readValue(resp.getBody(), KakaoUserVO.class);
        } catch (ClassCastException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vo;
    }

    public String logout(Member member) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com/")
                .path("/v1/user/logout")
                .queryParam("target_id_type", "user_id")
                .queryParam("target_id", member.getUuid())
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.<Void>post(uri)
                .header("Authorization", "KakaoAK " + CLIENT_SECRET)
                .build();

        Map<String, Object> map = connectResponseMap(req);

        return String.valueOf(map.get("id"));
    }

    public void disconnect(Member member) throws IOException {

        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v1/user/unlink")
                .queryParam("target_id_type", "user_id")
                .queryParam("target_id", member.getUuid())
                .encode()
                .build()
                .toUri();

        RequestEntity<Void> req = RequestEntity.<Void>post(uri)
                .header("Authorization", "KakaoAK " + CLIENT_SECRET)
                .build();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

    }

    @Override
    public RequestEntity<Void> getMethodRequestEntity(URI uri) throws IOException {

        RequestEntity<Void> req = RequestEntity.<Void>get(uri).build();

        return req;
    }
    @Override
    public RequestEntity<Void> postMethodRequestEntity(URI uri) throws IOException {

        RequestEntity<Void> req = RequestEntity.<Void>post(uri)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .build();

        return req;
    }

    @Override
    public KakaoTokenVO connect(RequestEntity<Void> req) throws IOException {

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

    public Map<String, Object> connectResponseMap(RequestEntity<Void> req) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        ObjectMapper om = new ObjectMapper();
        Map<String, Object> map = null;

        try {
             map = om.readValue(resp.getBody(), Map.class);
        } catch (ClassCastException e) {
            log.info(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

}
