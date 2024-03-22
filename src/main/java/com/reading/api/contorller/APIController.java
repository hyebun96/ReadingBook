package com.reading.api.contorller;

import com.reading.api.service.APIService;
import com.reading.book.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {

    private final APIService apiService;

    @GetMapping("/search")
    public Map<String, Object> moreSearch(@RequestParam("title") String title, @ModelAttribute PageRequestDTO pageRequestDTO) throws IOException {

        Map<String, Object> map = apiService.allSearchByTitleInNaver(title, pageRequestDTO);

        return map;
    }
}
