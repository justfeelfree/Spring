package com.firstfewlines.controller;

import com.firstfewlines.domain.WebService;
import com.firstfewlines.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@RestController
public class BasicRestController {

    @Autowired
    BasicService basicService;

    @RequestMapping(value = "/rest/", method = RequestMethod.GET)
    public ResponseEntity<List<WebService>> getAllWebServices() {
        List<WebService> webServices = new ArrayList<>();
        Iterable<WebService> iterable = basicService.getWebServices(orderBy());
        iterable.forEach(webServices::add);
        if(webServices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(webServices, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebService> getWebServiceById(@PathVariable("id") int id) {
        WebService webService = basicService.findOne(id);
        if (webService == null) {
            return new ResponseEntity<WebService>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(webService, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createWebService(@RequestBody WebService webService, UriComponentsBuilder ucBuilder) {
        if (basicService.exists(webService.getId())) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        basicService.addWebService(webService);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/rest/{id}").buildAndExpand(webService.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/fill", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> fillWebService(@RequestBody List<WebService> webServices) {
        webServices.stream()
                .filter(webService -> !basicService.exists(webService.getId()))
                .forEach(basicService::addWebService);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<WebService> deleteWebService(@PathVariable("id") int id) {
        WebService webService = basicService.findOne(id);
        if (webService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        basicService.deleteById(id);
        return new ResponseEntity<WebService>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/", method = RequestMethod.DELETE)
    public ResponseEntity<WebService> deleteAll() {
        basicService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/rest/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebService> updateWebService(@PathVariable("id") int id, @RequestBody WebService webService) throws Exception {
        WebService currentService = basicService.findOne(id);

        if (currentService == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentService.setId(webService.getId());
        currentService.setName(webService.getName());
        currentService.setDateContract(webService.getDateContract());
        currentService.setPrice(webService.getPrice());
        basicService.addWebService(currentService);

        return new ResponseEntity<>(currentService, HttpStatus.OK);
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
