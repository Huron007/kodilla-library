package com.example.kodillalibrary.service;

import com.example.kodillalibrary.entity.Copy;
import com.example.kodillalibrary.entity.Reader;
import com.example.kodillalibrary.entity.Rental;
import com.example.kodillalibrary.entity.Status;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.exception.RentalNotFoundException;
import com.example.kodillalibrary.repository.CopyRepository;
import com.example.kodillalibrary.repository.ReaderRepository;
import com.example.kodillalibrary.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalDbService {
    
    private final RentalRepository rentalRepository;
    private final ReaderRepository readerRepository;
    private final CopyRepository copyRepository;

    public List<Rental> getAllRentals(){
        return rentalRepository.findAll();
    }

    public Rental getRental(final Long rentalId) throws RentalNotFoundException {
        if(rentalRepository.existsById(rentalId)){
            return rentalRepository.findById(rentalId).get();
        } else {
            throw new RentalNotFoundException();
        }
    }

    public Rental saveRental(final Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental updateRental(final Rental rental) throws RentalNotFoundException {
        if(rentalRepository.existsById(rental.getId())){
            return rentalRepository.save(rental);
        } else {
            throw new RentalNotFoundException();
        }
    }

    public void deleteRental(final Long rentalId) throws RentalNotFoundException {
        if(rentalRepository.existsById(rentalId)){
            rentalRepository.deleteById(rentalId);
        } else {
            throw new RentalNotFoundException();
        }
    }

    public Rental rentABook(final Long readerId, final Long copyId) throws ReaderNotFoundException, CopyNotFoundException {
        if(readerRepository.existsById(readerId)){
            if(copyRepository.existsById(copyId)){
                Copy copy = copyRepository.findById(copyId).get();
                copy.setStatus(Status.RENTED);
                Copy updatedCopy = copyRepository.save(copy);
                return rentalRepository.save(Rental.builder().reader(readerRepository.findById(readerId).get()).copy(updatedCopy).returnDate(LocalDate.now().plusMonths(24)).build());
            } else {
                throw new CopyNotFoundException();
            }
        } else {
            throw new ReaderNotFoundException();
        }
    }

    public Rental returnABook(final Long rentalId) throws RentalNotFoundException , CopyNotFoundException {
        if(rentalRepository.existsById(rentalId)){
            Rental rental = rentalRepository.findById(rentalId).get();
            rental.setReturnDate(LocalDate.now());
            Copy copy = copyRepository.findById(rental.getCopy().getId()).get();
            copy.setStatus(Status.AVAILABLE);
            copyRepository.save(copy);
            return rentalRepository.save(rental);
        } else {
            throw new RentalNotFoundException();
        }
    }
}
