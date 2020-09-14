package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.Curio;
import org.springframework.data.repository.CrudRepository;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CurioRepository extends CrudRepository<Curio,Long> {

    List<Curio> getAllByLocalDate(LocalDate localDate);
}
