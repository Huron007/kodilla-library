package com.example.kodillalibrary.controller;

import com.example.kodillalibrary.dto.RentalDto;
import com.example.kodillalibrary.entity.Rental;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.exception.RentalNotFoundException;
import com.example.kodillalibrary.mapper.RentalMapper;
import com.example.kodillalibrary.service.RentalDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/rental")
public class RentalController {

    private final RentalDbService service;
    private final RentalMapper rentalMapper;

    @GetMapping
    public ResponseEntity<List<RentalDto>> getRentals(){
        List<Rental> rentals = service.getAllRentals();
        return ResponseEntity.ok(rentalMapper.mapToRentalDtoList(rentals));
    }

    @GetMapping(value = "{rentalId}")
    public ResponseEntity<RentalDto> getRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(service.getRental(rentalId)));
    }

    @DeleteMapping(value = "{rentalId}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long rentalId) throws RentalNotFoundException {
        service.deleteRental(rentalId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalDto> updateRental(@RequestBody RentalDto rentalDto) throws RentalNotFoundException, CopyNotFoundException, ReaderNotFoundException {
        Rental rental = rentalMapper.mapToRental(rentalDto);
        Rental savedRental = service.updateRental(rental);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(savedRental));
    }

    @PutMapping(value = "ReturnABook/{rentalId}")
    public ResponseEntity<RentalDto> returnABook(@PathVariable Long rentalId) throws RentalNotFoundException, CopyNotFoundException{
        Rental rental = service.returnABook(rentalId);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rental));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRental(@RequestBody RentalDto rentalDto) throws CopyNotFoundException, ReaderNotFoundException{
        Rental rental = rentalMapper.mapToRental(rentalDto);
        service.saveRental(rental);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "RentABook/{readerId}/{copyId}")
    public ResponseEntity<RentalDto> rentABook(@PathVariable Long readerId, @PathVariable Long copyId) throws ReaderNotFoundException, CopyNotFoundException {
        Rental rental = service.rentABook(readerId, copyId);
        return ResponseEntity.ok(rentalMapper.mapToRentalDto(rental));
    }
}
