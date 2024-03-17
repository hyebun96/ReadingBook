package com.reading.book.service;

import com.reading.api.NaverBookAPI;
import com.reading.api.domain.NaverResultVO;
import com.reading.book.domain.Book;
import com.reading.book.domain.BookDetailResponseDTO;
import com.reading.book.domain.BookListResponseDTO;
import com.reading.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookService {

    private final BookRepository bookRepository;

    private final NaverBookAPI naverBookAPI;

    private final ModelMapper modelMapper;

    public List<BookListResponseDTO> allSearchByIsbnInNaver(String title) throws IOException {

        NaverResultVO naverResultVO = naverBookAPI.searchBookAll(title);

        List<Book> items = naverResultVO.getItems();
        List<BookListResponseDTO> searchBookList = items.stream()
                .map(book -> modelMapper.map(book, BookListResponseDTO.class))
                .toList();

        return searchBookList;
    }

    public Book searchByIsbnInNaver(String isbn) throws IOException {

        NaverResultVO naverResultVO = naverBookAPI.searchBookDetail(isbn);
        Book book = naverResultVO.getItems().get(0);

        return book;
    }

    public String save(String isbn) throws IOException {

        Book book;

        if(!bookRepository.existsByIsbn(isbn)){
            book = searchByIsbnInNaver(isbn);
            book = bookRepository.save(book);
        } else {
            book = bookRepository.findByIsbn(isbn).orElseThrow();
        }

        return book.getIsbn();
    }

    public BookDetailResponseDTO searchDetail(String isbn) throws  IOException {

        Book book;

       if(!bookRepository.existsByIsbn(isbn)){
           book = searchByIsbnInNaver(isbn);
       } else {
           book = bookRepository.findByIsbn(isbn).orElseThrow();
       }
        return modelMapper.map(book, BookDetailResponseDTO.class);
    }

}
