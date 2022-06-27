package com.example.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReaderDto {
    private Long readerId;
    private String name;
    private String surname;
    private LocalDate created;
}
