package com.example.kodillalibrary.service;

import com.example.kodillalibrary.entity.Book;
import com.example.kodillalibrary.exception.BookNotFoundException;
import com.example.kodillalibrary.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookDbService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(final Long bookId) throws BookNotFoundException {
        if(bookRepository.existsById(bookId)){
            return bookRepository.findById(bookId).get();
        } else {
            throw new BookNotFoundException();
        }
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(final Book book) throws BookNotFoundException {
        if(bookRepository.existsById(book.getId())){
            return bookRepository.save(book);
        } else {
            throw new BookNotFoundException();
        }
    }

    public void deleteBook(final Long bookId) throws BookNotFoundException {
        if(bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException();
        }
    }
}
