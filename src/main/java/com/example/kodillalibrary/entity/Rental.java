package com.example.kodillalibrary.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rentalId", nullable = false, unique = true)
    private Long id;

    @OneToOne
    @JoinColumn(name = "copyId")
    private Copy copy;

    @OneToOne
    @JoinColumn(name = "userId")
    private Reader reader;

    @CreationTimestamp
    @Column(name = "rentalDate", nullable = false, updatable = false)
    private LocalDate rentalDate;

    @Column(name = "returnDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
