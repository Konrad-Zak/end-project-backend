package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

    @Override
    List<AppUser>findAll();

    Optional<AppUser> findByUsername(String username);

    @Override
    Optional<AppUser> findById(Long aLong);

    Boolean existsByUsername(String username);

}
