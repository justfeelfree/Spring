package com.firstfewlines.controller;

import com.firstfewlines.domain.WebService;
import com.firstfewlines.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/findone")
public class FindOneController {

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("findone");
        List<WebService> services = new ArrayList<>();
        BasicController.addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }
}
