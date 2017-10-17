package com.firstfewlines.service;

import com.firstfewlines.domain.WebService;
import com.firstfewlines.repository.WebServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BasicService {

    @Autowired
    WebServicesRepository repository;

    public WebService addWebService(WebService webService) {
        repository.save(webService);
        return webService;
    }

    public WebService findOne(Integer id) {
        return repository.findOne(id);
    }

    public Iterable<WebService> getWebServices(Sort sort){
        return repository.findAll(sort);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteById(Integer id) {
        repository.delete(id);
    }

    public boolean exists(Integer id) {
        return repository.exists(id);
    }
}
