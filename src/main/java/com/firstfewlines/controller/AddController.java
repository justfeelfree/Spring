package com.firstfewlines.controller;

import com.firstfewlines.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/add")
public class AddController {

    @Autowired
    BasicService basicService;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("add");
        BasicController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
