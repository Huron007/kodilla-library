package com.example.kodillalibrary.repository;

import com.example.kodillalibrary.entity.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {

    @Override
    List<Rental> findAll();

    @Override
    Optional<Rental> findById(Long id);

    @Override
    Rental save(Rental rental);

    @Override
    void deleteById(Long id);
}
