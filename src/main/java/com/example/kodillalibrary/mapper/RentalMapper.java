package com.example.kodillalibrary.mapper;

import com.example.kodillalibrary.dto.RentalDto;
import com.example.kodillalibrary.entity.Rental;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.repository.CopyRepository;
import com.example.kodillalibrary.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public Rental mapToRental(final RentalDto rentalDto) throws CopyNotFoundException, ReaderNotFoundException{
        if(copyRepository.existsById(rentalDto.getCopyId())){
            if(readerRepository.existsById(rentalDto.getReaderId())){
                if(rentalDto.getRentalId() == null){
                    return new Rental();
                } else {
                    return Rental.builder()
                            .id(rentalDto.getRentalId())
                            .copy(copyRepository.findById(rentalDto.getCopyId()).get())
                            .reader(readerRepository.findById(rentalDto.getReaderId()).get())
                            .rentalDate(rentalDto.getRentalDate())
                            .returnDate(rentalDto.getReturnDate())
                            .build();
                }
            } else {
                throw new ReaderNotFoundException();
            }
        } else {
            throw new CopyNotFoundException();
        }
    }

    public RentalDto mapToRentalDto(final Rental rental){
        return new RentalDto(
                rental.getId(),
                rental.getCopy().getId(),
                rental.getReader().getId(),
                rental.getRentalDate(),
                rental.getReturnDate()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList){
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}
