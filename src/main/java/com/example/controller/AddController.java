package com.example.controller;

import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/add")
public class AddController {

    @Autowired
    BasicService basicService;

    @Autowired
    UTILController util;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView("add");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/ok", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView addStudent(@RequestParam Integer id,
                            @RequestParam String name,
                            @RequestParam String dateContract,
                            @RequestParam BigDecimal price) throws Exception {

        List<String> messages = new ArrayList<>();
        messages.add("Trying to add new Web Service... ");
        ModelAndView modelAndView = new ModelAndView("add");
        try {
            messages.add(util.addWebServiceGetMessage(id, name, dateContract, price));
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
