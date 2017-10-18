package com.firstfewlines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/examples")
public class ExamplesController {

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView examples() {
        ModelAndView modelAndView = new ModelAndView("examples");
        BasicController.addUserName(modelAndView);
        return modelAndView;
    }

}