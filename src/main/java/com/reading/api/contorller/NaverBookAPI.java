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
import java.util.Map;

@Component
public class NaverBookAPI implements APIInterface<NaverResultVO> {

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String CLIENT_SECRET;

    public NaverResultVO searchBookAll(
            @RequestParam("title") final String title,
            PageRequestDTO pageRequestDTO) throws IOException {

        String path = "/v1/search/book.json";

        Map<String, String> map = Map.of("key", "query",
                                        "value", title,
                                        "display", String.valueOf(pageRequestDTO.getDisplay()),
                                        "start", String.valueOf(pageRequestDTO.getStart()));

        NaverResultVO resultVO = connect(path, map);

        return resultVO;
    }

    public NaverResultVO searchBookOne(@RequestParam("title") final String title) throws IOException {

        String path = "/v1/search/book.json";

        Map<String, String> map = Map.of("key", "query",
                                        "vlaue", title,
                                        "display", "1",
                                        "start", "1");

        NaverResultVO resultVO = connect(path, map);

        return resultVO;
    }

    public NaverResultVO searchBookDetail(@RequestParam("isbn") final String isbn) throws IOException {

        String path = "/v1/search/book_adv.json";

        Map<String, String> map = Map.of("key", "d_isbn",
                                        "value","isbn",
                                        "display", "1",
                                        "start", "1");

        NaverResultVO resultVO = connect(path, map);

        return resultVO;
    }

    @Override
    public NaverResultVO connect(String path, Map<String, String> map) throws IOException{

        // Spring URI를 생성할때 편리하게 구현할 수 있도록 도와주는 클래스. 자동 encode
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path(path)
                .queryParam(map.get("key"), map.get("value"))
                .queryParam("display", map.get("display"))
                .queryParam("start", map.get("start"))
                .encode()
                .build()
                .toUri();

        // Spring 요청 제공 클래스. Header 생성자 파라미터
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", CLIENT_ID)
                .header("X-Naver-Client-Secret", CLIENT_SECRET)
                .build();


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



