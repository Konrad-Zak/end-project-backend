package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppUserMessage;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppUserMessageRepository extends CrudRepository<AppUserMessage,Long> {

    @Override
    List<AppUserMessage>findAll();

    List<AppUserMessage>findAllByLocalDateBefore(LocalDate localDate);

    Boolean existsByLocalDateBefore(LocalDate localDate);
}
