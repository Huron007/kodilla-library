package com.example.kodillalibrary.service;

import com.example.kodillalibrary.entity.Book;
import com.example.kodillalibrary.entity.Copy;
import com.example.kodillalibrary.entity.Status;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.repository.BookRepository;
import com.example.kodillalibrary.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CopyDbService {
    
    private final CopyRepository copyRepository;
    private final BookRepository bookRepository;

    public List<Copy> getAllCopies(){
        return copyRepository.findAll();
    }

    public Copy getCopy(final Long copyId) throws CopyNotFoundException {
        if(copyRepository.existsById(copyId)){
            return copyRepository.findById(copyId).get();
        } else {
            throw new CopyNotFoundException();
        }
    }

    public Copy saveCopy(final Copy copy) {
        return copyRepository.save(copy);
    }

    public Copy updateCopy(final Copy copy) throws CopyNotFoundException {
        if(copyRepository.existsById(copy.getId())){
            return copyRepository.save(copy);
        } else {
            throw new CopyNotFoundException();
        }
    }

    public void deleteCopy(final Long copyId) throws CopyNotFoundException {
        if(copyRepository.existsById(copyId)){
            copyRepository.deleteById(copyId);
        } else {
            throw new CopyNotFoundException();
        }
    }

    public Copy changeCopyStatus(final Long copyId, final Status status) throws CopyNotFoundException{
        if(copyRepository.existsById(copyId)){
            Copy copy = copyRepository.findById(copyId).get();
            copy.setStatus(status);
            return copyRepository.save(copy);
        } else {
            throw new CopyNotFoundException();
        }
    }

    public List<Copy> getAllCopiesWithGivenTitleAndStatus(final String title, final Status status) {
        Long bookId = bookRepository.findByTitle(title).get().getId();
        return copyRepository.findAll().stream()
                .filter(e -> e.getStatus().equals(status))
                .filter(e -> e.getBook().getId().equals(bookId))
                .collect(Collectors.toList());
    }
}
