package com.example.service;

import com.example.domain.WebService;
import com.example.repository.WebServicesRepository;
import com.example.repository.specification.WebServiceSpecification;
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

    public Iterable<WebService> getWebServices(){
        return repository.findAll();
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

    public Iterable<WebService> getWithCriteria(String suffix){
        return repository.findAll(WebServiceSpecification.nameEndsWith(suffix));
    }

}
