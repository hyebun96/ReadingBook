package com.reading.api.service;

import com.reading.api.contorller.NaverBookAPI;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.domain.Book;
import com.reading.book.dto.BookListResponseDTO;
import com.reading.book.dto.PageRequestDTO;
import com.reading.book.dto.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class APIService {

    private final NaverBookAPI naverBookAPI;

    private final ModelMapper modelMapper;

    public Map<String, Object> allSearchByTitleInNaver(String title, PageRequestDTO pageRequestDTO) throws IOException {

        NaverResultVO naverResultVO = naverBookAPI.searchBookAll(title, pageRequestDTO);
        PageResponseDTO pageResponseDTO = modelMapper.map(naverResultVO, PageResponseDTO.class);
        List<BookListResponseDTO> searchBookList = null;
        Map<String, Object> result = new HashMap<>();

        if(pageResponseDTO.getTotal() > 0) {
            pageResponseDTO.setPageAndNext();

            searchBookList = naverResultVO.getItems().stream()
                                           .map(book -> modelMapper.map(book, BookListResponseDTO.class))
                                           .toList();

            result.put("exist", true);
        }else {
            result.put("exist", false);
        }

        result.put("title", title);
        result.put("searchBookList", searchBookList);
        result.put("pageResponseDTO", pageResponseDTO);

        return result;
    }

    public Book searchByIsbnInNaver(String isbn) throws IOException {

        NaverResultVO naverResultVO = naverBookAPI.searchBookDetail(isbn);
        Book book = naverResultVO.getItems().get(0);

        return book;
    }

}
