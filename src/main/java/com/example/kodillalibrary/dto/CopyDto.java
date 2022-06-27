package com.example.kodillalibrary.dto;

import com.example.kodillalibrary.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CopyDto {
    private Long copyId;
    private Long bookId;
    private Status status;
}
