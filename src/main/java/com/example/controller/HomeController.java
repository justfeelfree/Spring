package com.example.controller;

import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
class HomeController {

    @Autowired
    BasicService basicService;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        UTILController.addUserName(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView error() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Error. Incorrect username or password.");
        ModelAndView modelAndView = new ModelAndView("home");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView login() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Error. Please log in.");
        ModelAndView modelAndView = new ModelAndView("home");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/login-success", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView loginSuccess() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("You have successfully logged in.");
        messages.add(String.format("Hello, %s. Welcome to SpringDEMO application.", UTILController.getUserName()));
        ModelAndView modelAndView = new ModelAndView("success");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/logout-success", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView logoutSuccess() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("You have successfully logged out.");
        ModelAndView modelAndView = new ModelAndView("home");
        if(!UTILController.getUserName().isEmpty()) {
            modelAndView.addObject("username", UTILController.getUserName());
            modelAndView.addObject("logout", UTILController.getUserName() + ", Sign Out");
        }
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/loggingout", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView loggingout() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Click the button if you really want to sign out.");
        ModelAndView modelAndView = new ModelAndView("success");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }




}
