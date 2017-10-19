package com.example.service;

import com.example.domain.WebService;
import com.example.repository.WebServiceJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Service
public class AdvancedService {

    @Autowired
    WebServiceJpaRepository repository;

    public List<WebService> findAllByName() {
        return repository.findAllByName();
    }

    public List<WebService> simpleQuery() {
        return repository.simpleQuery();
    }

    public List<WebService> nativeQuery() {
        return repository.nativeQuery();
    }

    public List<WebService> getAllWithJsonData() {
        return repository.getAllWithJsonData();
    }
}
