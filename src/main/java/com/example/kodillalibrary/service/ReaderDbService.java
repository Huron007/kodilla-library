package com.example.kodillalibrary.service;

import com.example.kodillalibrary.entity.Reader;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;

    public List<Reader> getAllReaders(){
        return readerRepository.findAll();
    }

    public Reader getReader(final Long readerId) throws ReaderNotFoundException {
        if(readerRepository.existsById(readerId)){
            return readerRepository.findById(readerId).get();
        } else {
            throw new ReaderNotFoundException();
        }
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader updateReader(final Reader reader) throws ReaderNotFoundException {
        if(readerRepository.existsById(reader.getId())){
            return readerRepository.save(reader);
        } else {
            throw new ReaderNotFoundException();
        }
    }

    public void deleteReader(final Long readerId) throws ReaderNotFoundException {
        if (readerRepository.existsById(readerId)){
            readerRepository.deleteById(readerId);
        } else {
            throw new ReaderNotFoundException();
        }
    }
}
