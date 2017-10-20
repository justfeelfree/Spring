package com.example.controller;

import com.example.domain.MyJson;
import com.example.domain.WebService;
import com.example.service.BasicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;

/**
 * @author Kirill Nigmatullin
 */
@Controller
public class UTILController {

    @Autowired
    private BasicService basicService;

    public String addWebServiceGetMessage(Integer id, String name, String dateContract, BigDecimal price) throws Exception {
        WebService service = basicService.findOne(id);
        String message;
        if(service == null) {
            service = new WebService();
            service.setId(id);
            service.setName(name);
            service.setDateContract(dateContract);
            service.setPrice(price);
            service.setJsonData(getJSON(service));
            basicService.addWebService(service);
            System.out.println(service.toString());
            message = "WebService with ID: " + id + " successful added";
        } else {
            System.out.println("ERROR, already exists: " + service.toString());
            message = "WebService with ID: " + id + " already exists";
        }
        return message;
    }

    public String updateWebServiceGetMessage(Integer id, String name, String dateContract, BigDecimal price) throws Exception {
        WebService service = basicService.findOne(id);
        String message;
        if(service != null) {
            service.setName(name);
            service.setDateContract(dateContract);
            service.setPrice(price);
            service.setJsonData(getJSON(service));
            basicService.addWebService(service);
            message = "WebService with ID: " + service.getId() + " successful updated";
        } else {
            message = "WebService with ID: " + id + " not exists";
        }
        return message;
    }

    public static MyJson getJSON(WebService webService) throws JsonProcessingException {
        MyJson json = new MyJson();
        json.setId(webService.getId());
        json.setDateContract(webService.getDateContract());
        json.setName(webService.getName());
        json.setPrice(webService.getPrice());
        return json;
    }

    public static String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName().startsWith("anonymous") ? "" : auth.getName();
    }

    public static void addUserName(ModelAndView modelAndView) {
        if(!getUserName().isEmpty()) {
            modelAndView.addObject("username", getUserName());
            modelAndView.addObject("logout", getUserName() + ", Sign Out");
        }
    }

    public static Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }
}
