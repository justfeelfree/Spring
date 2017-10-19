package com.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.domain.WebService;
import com.example.service.AdvancedService;
import com.example.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/")
class BasicController {

    @Autowired
    BasicService basicService;

    @Autowired
    AdvancedService advancedService;

    @Autowired
    BasicRestController restController;

    @RequestMapping(value = "/service", method = RequestMethod.GET)
    ModelAndView service() {
        ModelAndView modelAndView = new ModelAndView("service");
        addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        addUserName(modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/login-error", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView error() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Error. Incorrect username or password.");
        ModelAndView modelAndView = new ModelAndView("home");
        addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView login() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Error. Please log in.");
        ModelAndView modelAndView = new ModelAndView("home");
        addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/login-success", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView loginSuccess() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("You have successfully logged in.");
        messages.add(String.format("Hello, %s. Welcome to SpringDEMO application.", getUserName()));
        ModelAndView modelAndView = new ModelAndView("success");
        addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/logout-success", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView logoutSuccess() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("You have successfully logged out.");
        ModelAndView modelAndView = new ModelAndView("home");
        if(!getUserName().isEmpty()) {
            modelAndView.addObject("username", getUserName());
            modelAndView.addObject("logout", getUserName() + ", Sign Out");
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
        addUserName(modelAndView);
        modelAndView.addObject("messages", messages);
        return modelAndView;
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView fillData() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to fill in the data base...");
        ModelAndView modelAndView = new ModelAndView("service");
        try {
            messages.add(addWebServiceGetMessage(1, "AlfaCS_EQ_WSPartnerBaseInfo22", "2005-05-22", BigDecimal.valueOf(100)));
            messages.add(addWebServiceGetMessage(2, "AlfaCS_EQ_WSPartnerReport11", "2010-07-13", BigDecimal.valueOf(55.88)));
            messages.add(addWebServiceGetMessage(3, "AlfaCS_EQ_WSPartnerReportHistory11", "2011-01-08", BigDecimal.valueOf(560.00)));
            messages.add(addWebServiceGetMessage(4, "AlfaCS_EQ_WSProductCatalogPackageTarif10", "2008-02-01", BigDecimal.valueOf(200)));
            messages.add(addWebServiceGetMessage(5, "AlfaCS_EQ_WSStatementDepositInfo13", "2009-12-01", BigDecimal.valueOf(546.566)));
            messages.add(addWebServiceGetMessage(6, "AlfaCS_EQ_WSAccountCloseRestriction10", "2015-08-15", BigDecimal.valueOf(100)));
            messages.add(addWebServiceGetMessage(7, "AlfaCS_EQ_WSProductCatalogDocGroup10", "2016-07-29", BigDecimal.valueOf(42.4)));
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
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
        addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/named", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView named() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a NamedQuery...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.findAllByNameAndPriceCondition();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/simple", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView simple() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Simple Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.simpleQuery();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/native", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView nativeQuery() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Native Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            services = advancedService.nativeQuery();

            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", services);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);

        return modelAndView;
    }

    @RequestMapping(value = "/criteria", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView criteriaQuery() throws Exception {
        List<String> messages = new ArrayList<>();
        messages.add("Trying to find with a Criteria Query...");
        ModelAndView modelAndView = new ModelAndView("example");
        List<WebService> services;
        try {
            modelAndView.addObject("messages", messages);
            modelAndView.addObject("services", basicService.getWithCriteria());
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);

        return modelAndView;
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    @RequestMapping(value = "/post-add", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView addStudent(@RequestParam Integer id,
                            @RequestParam String name,
                            @RequestParam String dateContract,
                            @RequestParam BigDecimal price) throws Exception {

        List<String> messages = new ArrayList<>();
        messages.add("Trying to add new Web Service... ");
        ModelAndView modelAndView = new ModelAndView("add");
        try {
            messages.add(addWebServiceGetMessage(id, name, dateContract, price));
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/post-findone", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView findById(@RequestParam Integer id) throws Exception {

        List<String> messages = new ArrayList<>();
        messages.add("Trying to find Web Service... ");
        ModelAndView modelAndView = new ModelAndView("findone");
        List<WebService> services = new ArrayList<>();
        try {
            WebService service = basicService.findOne(id);

            if (service != null) {
                services.add(service);
                messages.add("Web Service with ID: " + id + " successful found");
            } else {
                throw new Exception("Web Service with ID: " + id + " not exists");
            }
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }

    @RequestMapping(value = "/post-delete", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView deleteById(@RequestParam Integer id) throws Exception {

        List<String> messages = new ArrayList<>();
        messages.add("Trying to delete Web Service... ");
        ModelAndView modelAndView = new ModelAndView("delete");
        try {
            WebService service = basicService.findOne(id);

            if (service != null) {
                basicService.deleteById(id);
                messages.add("Web Service with ID: " + id + " successful deleted");
            } else {
                throw new Exception("Web Service with ID: " + id + " not exists");
            }
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);
        modelAndView.addObject("services", basicService.getWebServices(orderBy()));
        return modelAndView;
    }

    @RequestMapping(value = "/post-update", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
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
            messages.add(updateWebServiceGetMessage(id, name, dateContract, price));
            services.add(basicService.findOne(id));
            modelAndView.addObject("messages", messages);
        }
        catch (Exception ex){
            messages.add(ex.getMessage());
            modelAndView.addObject("messages", messages);
        }
        addUserName(modelAndView);
        modelAndView.addObject("services", services);
        return modelAndView;
    }

    private String addWebServiceGetMessage(Integer id, String name, String dateContract, BigDecimal price) throws Exception {
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

    private String updateWebServiceGetMessage(Integer id, String name, String dateContract, BigDecimal price) throws Exception {
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

    public String getJSON(WebService webService) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        json = mapper.writeValueAsString(webService);
        return json;
    }

    private static String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName().startsWith("anonymous") ? "" : auth.getName();
    }

    public static void addUserName(ModelAndView modelAndView) {
        if(!getUserName().isEmpty()) {
            modelAndView.addObject("username", getUserName());
            modelAndView.addObject("logout", getUserName() + ", Sign Out");
        }
    }
}
