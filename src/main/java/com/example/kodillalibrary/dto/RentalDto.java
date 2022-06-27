package com.example.kodillalibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RentalDto {
    private Long rentalId;
    private Long copyId;
    private Long readerId;
    private LocalDate rentalDate;
    private LocalDate returnDate;
}
