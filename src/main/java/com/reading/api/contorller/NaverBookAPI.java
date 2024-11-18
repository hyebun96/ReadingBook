package com.reading.api.contorller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.dto.PageRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@Component
public class NaverBookAPI implements APIInterface<NaverResultVO> {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String CLIENT_SECRET;

    public NaverResultVO searchBookAll(
            @RequestParam("title") final String title,
            @RequestParam("pageRequestDTO") PageRequestDTO pageRequestDTO) throws IOException {

        // Spring URI를 생성할때 편리하게 구현할 수 있도록 도와주는 클래스. 자동 encode
        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                                      .path("/v1/search/book.json")
                                      .queryParam("query", title)
                                      .queryParam("display", String.valueOf(pageRequestDTO.getDisplay()))
                                      .queryParam("start", String.valueOf(pageRequestDTO.getStart()))
                                      .encode()
                                      .build()
                                      .toUri();

        NaverResultVO resultVO = connect(getMethodRequestEntity(uri));

        return resultVO;
    }

    public NaverResultVO searchBookOne(@RequestParam("title") final String title) throws IOException {

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                                      .path("/v1/search/book.json")
                                      .queryParam("query", title)
                                      .queryParam("display", "1")
                                      .queryParam("start", "1")
                                      .encode()
                                      .build()
                                      .toUri();

        String path = "";

        NaverResultVO resultVO = connect(getMethodRequestEntity(uri));

        return resultVO;
    }

    public NaverResultVO searchBookDetail(@RequestParam("isbn") final String isbn) throws IOException {

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                                      .path("/v1/search/book_adv.json")
                                      .queryParam("d_isbn", isbn)
                                      .queryParam("display", "1")
                                      .queryParam("start", "1")
                                      .encode()
                                      .build()
                                      .toUri();

        NaverResultVO resultVO = connect(getMethodRequestEntity(uri));

        return resultVO;
    }

    @Override
    public RequestEntity<Void> getMethodRequestEntity(URI uri) throws IOException {

        // Spring 요청 제공 클래스. Header 생성자 파라미터
        RequestEntity<Void> req = RequestEntity.get(uri)
                                               .header("X-Naver-Client-Id", CLIENT_ID)
                                               .header("X-Naver-Client-Secret", CLIENT_SECRET)
                                               .build();
        return req;
    }

    @Override
    public RequestEntity<Void> postMethodRequestEntity(URI uri) throws IOException {

        // Spring 요청 제공 클래스. Header 생성자 파라미터
        RequestEntity<Void> req = RequestEntity.post(uri)
                                               .header("X-Naver-Client-Id", CLIENT_ID)
                                               .header("X-Naver-Client-Secret", CLIENT_SECRET)
                                               .build();

        return req;
    }

    @Override
    public NaverResultVO connect(RequestEntity<Void> req) throws IOException {

        // Spring 제공 restTemplate. Rest 방식 API를 호출할 수 있는 Spring 내장 클래스
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resp = restTemplate.exchange(req, String.class);

        // JSON 파싱 (Json 문자열을 객체로 만듦, 문서화)
        ObjectMapper om = new ObjectMapper();
        NaverResultVO resultVO = null;

        try {
            resultVO  = om.readValue(resp.getBody(), NaverResultVO.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultVO;
    }
}



