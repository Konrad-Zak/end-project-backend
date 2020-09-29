package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CURIOS")
public class Curio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "DATE")
    private LocalDate localDate;
}
