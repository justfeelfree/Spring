package com.example.controller;

import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kirill Nigmatullin
 */
@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    BasicService basicService;

    @Autowired
    UTILController util;

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView service() {
        ModelAndView modelAndView = new ModelAndView("service");
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(UTILController.orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView fillData() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to fill in the data base...");
        ModelAndView modelAndView = new ModelAndView("service");
        try {
            messages.add(util.addWebServiceGetMessage(1, "AlfaCS_EQ_WSPartnerBaseInfo22", "2005-05-22", BigDecimal.valueOf(100)));
            messages.add(util.addWebServiceGetMessage(2, "AlfaCS_EQ_WSPartnerReport11", "2010-07-13", BigDecimal.valueOf(55.88)));
            messages.add(util.addWebServiceGetMessage(3, "AlfaCS_EQ_WSPartnerReportHistory11", "2011-01-08", BigDecimal.valueOf(560.00)));
            messages.add(util.addWebServiceGetMessage(4, "AlfaCS_EQ_WSProductCatalogPackageTarif10", "2008-02-01", BigDecimal.valueOf(200)));
            messages.add(util.addWebServiceGetMessage(5, "AlfaCS_EQ_WSStatementDepositInfo13", "2009-12-01", BigDecimal.valueOf(546.566)));
            messages.add(util.addWebServiceGetMessage(6, "AlfaCS_EQ_WSAccountCloseRestriction10", "2015-08-15", BigDecimal.valueOf(100)));
            messages.add(util.addWebServiceGetMessage(7, "AlfaCS_EQ_WSProductCatalogDocGroup10", "2016-07-29", BigDecimal.valueOf(42.4)));
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(UTILController.orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/clean", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView cleanData() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to clean data...");
        ModelAndView modelAndView = new ModelAndView("service");
        try {
            basicService.deleteAll();
            String message = "Data successful cleaned";
            messages.add(message);
            modelAndView.addObject("messages", messages);

        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        UTILController.addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(UTILController.orderBy()));
        return modelAndView;
    }
}
