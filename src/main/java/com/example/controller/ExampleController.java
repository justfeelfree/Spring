package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/example")
public class ExampleController {


    @RequestMapping(method = RequestMethod.GET)
    ModelAndView example() {
        ModelAndView modelAndView = new ModelAndView("example");
        BasicController.addUserName(modelAndView);
        return modelAndView;
    }

}