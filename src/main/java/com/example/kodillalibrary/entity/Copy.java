package com.example.kodillalibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "copies")
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "copyId", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(name = "status")
    private Status status;

    public void setStatus(Status status) {
        this.status = status;
    }
}
