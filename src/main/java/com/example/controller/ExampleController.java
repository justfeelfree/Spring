package com.example.controller;

import com.example.domain.WebService;
import com.example.service.AdvancedService;
import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/example")
public class ExampleController {

    @Autowired
    AdvancedService advancedService;

    @Autowired
    BasicService basicService;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView example() {
        ModelAndView modelAndView = new ModelAndView("example");
        UTILController.addUserName(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/named", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView named() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a NamedQuery...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.findAllByNameEndsWith10();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/simple", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView simple() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Simple Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.getByPriceGreaterOrEquals100();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/native", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView nativeQuery() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Native Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.nativeQueryNameEndsWith13();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/criteria", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView criteriaQuery() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Criteria Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", basicService.getWithCriteria("11"));
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);

        return modelAndView;
    }
}