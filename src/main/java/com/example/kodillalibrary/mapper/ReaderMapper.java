package com.example.kodillalibrary.mapper;

import com.example.kodillalibrary.dto.ReaderDto;
import com.example.kodillalibrary.entity.Reader;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto){
        return Reader.builder()
                .id(readerDto.getReaderId())
                .name(readerDto.getName())
                .surname(readerDto.getSurname())
                .created(readerDto.getCreated())
                .build();
    }

    public ReaderDto mapToReaderDto(final Reader reader){
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getCreated()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList){
        return readerList.stream()
                .map(this::mapToReaderDto)
                .collect(Collectors.toList());
    }
}
