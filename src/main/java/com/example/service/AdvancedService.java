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

    public List<WebService> findAllByNameEndsWith10() {
        return repository.findAllByNameEndsWith10();
    }

    public List<WebService> getByPriceGreaterOrEquals100() {
        return repository.getByPriceGreaterOrEquals100();
    }

    public List<WebService> nativeQueryNameEndsWith13() {
        return repository.nativeQueryNameEndsWith13();
    }

    public List<WebService> nativeQueryPriceEquals100(BigDecimal price) {
        return repository.nativeQueryPriceEquals100(price);
    }
}
