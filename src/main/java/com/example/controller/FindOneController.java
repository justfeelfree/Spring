package com.example.controller;

import com.example.domain.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/findone")
public class FindOneController {

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView findOne() {
        ModelAndView modelAndView = new ModelAndView("findone");
        List<WebService> services = new ArrayList<>();
        BasicController.addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }
}
