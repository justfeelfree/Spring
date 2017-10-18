package com.firstfewlines.service;

import com.firstfewlines.domain.WebService;
import com.firstfewlines.repository.WebServiceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Service
public class AdvancedService {

    @Autowired
    WebServiceJpaRepository repository;

    public List<WebService> findAllByNameAndPriceCondition() {
        return repository.findAllByNameAndPriceCondition();
    }

    public List<WebService> simpleQuery() {
        return repository.simpleQuery();
    }
}
