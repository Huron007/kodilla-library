package com.example.kodillalibrary.controller;

import com.example.kodillalibrary.dto.BookDto;
import com.example.kodillalibrary.entity.Book;
import com.example.kodillalibrary.exception.BookNotFoundException;
import com.example.kodillalibrary.mapper.BookMapper;
import com.example.kodillalibrary.service.BookDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/book")
public class BookController {

        private final BookDbService service;
        private final BookMapper bookMapper;

        @GetMapping
        public ResponseEntity<List<BookDto>> getBooks(){
            List<Book> books = service.getAllBooks();
            return ResponseEntity.ok(bookMapper.mapToBookDtoList(books));
         }

        @GetMapping(value = "{bookId}")
        public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
            return ResponseEntity.ok(bookMapper.mapToBookDto(service.getBook(bookId)));
        }

        @DeleteMapping(value = "{bookId}")
        public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
            service.deleteBook(bookId);
            return ResponseEntity.ok().build();
        }

        @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) throws BookNotFoundException {
            Book book = bookMapper.mapToBook(bookDto);
            Book savedBook = service.updateBook(book);
            return ResponseEntity.ok(bookMapper.mapToBookDto(savedBook));
        }

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto){
            Book book = bookMapper.mapToBook(bookDto);
            service.saveBook(book);
            return ResponseEntity.ok().build();
        }
}
