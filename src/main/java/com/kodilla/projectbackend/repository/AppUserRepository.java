package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    @Override
    List<AppUser>findAll();

    AppUser findByUsername(String username);

}
