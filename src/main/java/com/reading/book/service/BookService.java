package com.reading.book.service;

import com.reading.api.service.APIService;
import com.reading.book.domain.Book;
import com.reading.book.dto.BookDetailResponseDTO;
import com.reading.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final APIService apiService;

    private final ModelMapper modelMapper;


    public String save(String isbn) throws IOException {

        Book book;

        if(!bookRepository.existsByIsbn(isbn)){
            book = apiService.searchByIsbnInNaver(isbn);
            book = bookRepository.save(book);
        } else {
            book = bookRepository.findByIsbn(isbn).orElseThrow();
        }

        return book.getIsbn();
    }

    public BookDetailResponseDTO searchDetail(String isbn) throws  IOException {

        Book book;

       if(!bookRepository.existsByIsbn(isbn)){
           book = apiService.searchByIsbnInNaver(isbn);
       } else {
           book = bookRepository.findByIsbn(isbn).orElseThrow();
       }
        return modelMapper.map(book, BookDetailResponseDTO.class);
    }

}
