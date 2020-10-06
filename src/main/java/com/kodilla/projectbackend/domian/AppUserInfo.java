package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS_INFO")
public class AppUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AppUserInfo that = (AppUserInfo) object;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(appUser, that.appUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, email, appUser);
    }
}
