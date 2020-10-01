package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS_CALORIE_INFO")
public class CalorieInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "WEIGHT")
    private Double appUserWeight;

    @Column(name = "FITNESS")
    private Double appUserFitness;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_CALORIE_ID")
    private AppUserCalorie appUserCalorie;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;

}
