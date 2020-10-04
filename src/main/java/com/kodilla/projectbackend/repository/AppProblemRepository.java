package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppProblem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppProblemRepository extends CrudRepository<AppProblem, Long> {

    @Override
    List<AppProblem> findAll();

}
