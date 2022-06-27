package com.example.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String title;
    private String author;
    private LocalDate releaseDate;
    private List<Long> copiesId;
}
