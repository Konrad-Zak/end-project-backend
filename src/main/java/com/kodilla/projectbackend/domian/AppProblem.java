package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PROBLEMS")
public class AppProblem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROBLEM_TEXT")
    private String text;

    @Column(name = "DATE")
    private Timestamp timestamp;
}
