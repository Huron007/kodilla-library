package com.example.kodillalibrary.controller;

import com.example.kodillalibrary.dto.ReaderDto;
import com.example.kodillalibrary.entity.Reader;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.mapper.ReaderMapper;
import com.example.kodillalibrary.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/reader")
public class ReaderController {

    private final ReaderDbService service;
    private final ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders(){
        List<Reader> readers = service.getAllReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping(value = "{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(service.getReader(readerId)));
    }

    @DeleteMapping(value = "{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long readerId) throws ReaderNotFoundException {
        service.deleteReader(readerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) throws ReaderNotFoundException {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = service.updateReader(reader);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(savedReader));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto){
        Reader reader = readerMapper.mapToReader(readerDto);
        service.saveReader(reader);
        return ResponseEntity.ok().build();
    }
}
