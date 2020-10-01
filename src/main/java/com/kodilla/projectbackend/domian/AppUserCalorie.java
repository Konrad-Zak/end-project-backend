package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS_CALORIES")
public class AppUserCalorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PROTEIN")
    private Double protein;

    @Column(name = "FAT")
    private Double fat;

    @Column(name = "CARBOHYDRATES")
    private Double carbohydrates;

    @Column(name = "CALORIES")
    private Double calories;
}
