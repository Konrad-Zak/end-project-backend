package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
public class AppUserDto implements UserDetails {
    private final static String ROLE = "ROLE_USER";
    private Long id;
    private String username;
    private String password;
    private String role;

    public AppUserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUserDto(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = ROLE;
    }

    public AppUserDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = ROLE;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
