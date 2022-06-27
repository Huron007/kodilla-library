package com.example.kodillalibrary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId", nullable = false, unique = true)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;

    @Column(name = "author", unique = true)
    private String author;

    @Column(name = "releaseDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @OneToMany(
            targetEntity = Copy.class,
            mappedBy = "book"
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Copy> copies = new ArrayList<>();
}
