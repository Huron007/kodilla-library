package com.example.kodillalibrary.mapper;

import com.example.kodillalibrary.dto.CopyDto;
import com.example.kodillalibrary.entity.Copy;
import com.example.kodillalibrary.exception.BookNotFoundException;
import com.example.kodillalibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CopyMapper {

    @Autowired
    private BookRepository bookRepository;

    public Copy mapToCopy(final CopyDto copyDto) throws BookNotFoundException {
        if (copyDto.getCopyId() == null) {
            return new Copy();
        } else {
            if(bookRepository.existsById(copyDto.getBookId())){
                return Copy.builder()
                        .id(copyDto.getCopyId())
                        .book(bookRepository.findById(copyDto.getBookId()).get())
                        .status(copyDto.getStatus())
                        .build();
            } else {
                throw new BookNotFoundException();
            }
        }
    }

    public CopyDto mapToCopyDto(final Copy copy){
        return new CopyDto(
                copy.getId(),
                copy.getBook().getId(),
                copy.getStatus()
        );
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copyList){
        return copyList.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
