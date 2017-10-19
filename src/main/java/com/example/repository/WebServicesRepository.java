package com.example.repository;

import com.example.domain.WebService;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebServicesRepository extends CrudRepository<WebService, Integer>, JpaSpecificationExecutor<WebService> {
    Iterable<WebService> findAll(Sort sort);
    Iterable<WebService> findAll();
}
