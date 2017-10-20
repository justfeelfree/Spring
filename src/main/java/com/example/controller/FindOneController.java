package com.example.controller;

import com.example.domain.WebService;
import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/findone")
public class FindOneController {

    @Autowired
    BasicService basicService;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView findOne() {
        ModelAndView modelAndView = new ModelAndView("findone");
        List<WebService> services = new ArrayList<>();
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }

    @RequestMapping(value = "/ok", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView findById(@RequestParam Integer id) throws Exception {

        List<String> messages = new ArrayList<>();
        messages.add("Trying to find Web Service... ");
        ModelAndView modelAndView = new ModelAndView("findone");
        List<WebService> services = new ArrayList<>();
        try {
            WebService service = basicService.findOne(id);

            if (service != null) {
                services.add(service);
                messages.add("Web Service with ID: " + id + " successful found");
            } else {
                throw new Exception("Web Service with ID: " + id + " not exists");
            }
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }
}
