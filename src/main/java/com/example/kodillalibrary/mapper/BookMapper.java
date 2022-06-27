package com.example.kodillalibrary.mapper;

import com.example.kodillalibrary.dto.BookDto;
import com.example.kodillalibrary.entity.Book;
import com.example.kodillalibrary.repository.CopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    private CopyRepository copyRepository;

    public Book mapToBook(final BookDto bookDto) {
        if(bookDto.getBookId() == null){
            return Book.builder().releaseDate(LocalDate.now()).build();
        } else if(!bookDto.getCopiesId().isEmpty()) {
            return Book.builder()
                    .id(bookDto.getBookId())
                    .title(bookDto.getTitle())
                    .author(bookDto.getAuthor())
                    .releaseDate(bookDto.getReleaseDate())
                    .copies(bookDto.getCopiesId().stream()
                            .map(e -> copyRepository.findById(e).orElseGet(null))
                            .filter(Objects::nonNull)
                            .collect(Collectors.toList()))
                    .build();
        } else {
            return Book.builder()
                    .id(bookDto.getBookId())
                    .title(bookDto.getTitle())
                    .author(bookDto.getAuthor())
                    .releaseDate(bookDto.getReleaseDate())
                    .copies(new ArrayList<>())
                    .build();
        }
    }

    public BookDto mapToBookDto(final Book book){
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseDate(),
                book.getCopies().stream()
                        .map(e -> e.getId())
                        .collect(Collectors.toList())
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToBookDto)
                .collect(Collectors.toList());
    }
}
