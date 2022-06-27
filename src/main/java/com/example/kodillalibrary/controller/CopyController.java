package com.example.kodillalibrary.controller;

import com.example.kodillalibrary.dto.CopyDto;
import com.example.kodillalibrary.entity.Copy;
import com.example.kodillalibrary.entity.Status;
import com.example.kodillalibrary.exception.BookNotFoundException;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.mapper.CopyMapper;
import com.example.kodillalibrary.service.CopyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/copy")
public class CopyController {

    private final CopyDbService service;
    private final CopyMapper copyMapper;

    @GetMapping
    public ResponseEntity<List<CopyDto>> getCopies(){
        List<Copy> copies = service.getAllCopies();
        return ResponseEntity.ok(copyMapper.mapToCopyDtoList(copies));
    }

    @GetMapping(value = "{title}/{status}")
    public ResponseEntity<List<CopyDto>> getAvailableCopiesWithGivenTitle(@PathVariable String title, @PathVariable Status status) {
        List<Copy> copies = service.getAllCopiesWithGivenTitleAndStatus(title, status);
        return ResponseEntity.ok(copyMapper.mapToCopyDtoList(copies));
    }

    @GetMapping(value = "{copyId}")
    public ResponseEntity<CopyDto> getCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        return ResponseEntity.ok(copyMapper.mapToCopyDto(service.getCopy(copyId)));
    }

    @DeleteMapping(value = "{copyId}")
    public ResponseEntity<Void> deleteCopy(@PathVariable Long copyId) throws CopyNotFoundException {
        service.deleteCopy(copyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CopyDto> updateCopy(@RequestBody CopyDto copyDto) throws CopyNotFoundException, BookNotFoundException {
        Copy copy = copyMapper.mapToCopy(copyDto);
        Copy savedCopy = service.updateCopy(copy);
        return ResponseEntity.ok(copyMapper.mapToCopyDto(savedCopy));
    }

    @PutMapping(value = "{copyId}/{status}")
    public ResponseEntity<CopyDto> updateCopyStatus(@PathVariable Long copyId,@PathVariable Status status) throws CopyNotFoundException {
        Copy copy = service.changeCopyStatus(copyId, status);
        return ResponseEntity.ok(copyMapper.mapToCopyDto(copy));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopy(@RequestBody CopyDto copyDto) throws BookNotFoundException{
        Copy copy = copyMapper.mapToCopy(copyDto);
        service.saveCopy(copy);
        return ResponseEntity.ok().build();
    }
}
