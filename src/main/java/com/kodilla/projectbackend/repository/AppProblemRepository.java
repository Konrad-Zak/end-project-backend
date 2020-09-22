package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppProblem;
import org.springframework.data.repository.CrudRepository;

public interface AppProblemRepository extends CrudRepository<AppProblem, Long> {
}
