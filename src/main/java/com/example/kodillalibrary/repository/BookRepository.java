package com.example.kodillalibrary.repository;

import com.example.kodillalibrary.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    Optional<Book> findById(Long id);

    @Override
    Book save(Book book);

    @Override
    void deleteById(Long id);

    Optional<Book> findByTitle(String title);
}
