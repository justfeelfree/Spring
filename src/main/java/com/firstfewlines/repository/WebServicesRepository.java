package com.firstfewlines.repository;

import com.firstfewlines.domain.WebService;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebServicesRepository extends CrudRepository<WebService, Integer> {
    Iterable<WebService> findAll(Sort sort);
}
