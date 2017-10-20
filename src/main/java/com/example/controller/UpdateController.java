package com.example.controller;

import com.example.domain.WebService;
import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    BasicService basicService;

    @Autowired
    UTILController util;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView update() {
        ModelAndView modelAndView = new ModelAndView("update");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/ok", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView updateStudent(@RequestParam Integer id,
                               @RequestParam String name,
                               @RequestParam String dateContract,
                               @RequestParam BigDecimal price ) throws Exception {

        ModelAndView modelAndView = new ModelAndView("update");
        List<String> messages = new ArrayList<>();
        List<WebService> services = new ArrayList<>();
        try {
            messages.add("Trying to update Web Service... ");
            if(name.isEmpty()) throw new Exception("Fill the name. If you don't want to change it fill previous value");
            if(dateContract.isEmpty()) throw new Exception("Fill the date of contract. If you don't want to change it fill previous value");
            messages.add(util.updateWebServiceGetMessage(id, name, dateContract, price));
            services.add(basicService.findOne(id));
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

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
