package com.example.service;

import com.example.domain.MyJson;
import com.example.domain.WebService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Kirill Nigmatullin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AdvancedServiceTest {

    private String testWSName = "WSTest10";
    private String testDate = "15-05-2011";
    private int testId = 100;
    private BigDecimal testPrice = BigDecimal.valueOf(200);

    @Autowired
    private BasicService basicService;

    @Autowired
    private AdvancedService advancedService;

    @Before
    public void setUp() throws Exception {
        basicService.deleteAll();
        basicService.addWebService(getTestWebService(1, "AlfaCS_EQ_WSPartnerBaseInfo22", "2005-05-22", BigDecimal.valueOf(100)));
        basicService.addWebService(getTestWebService(2, "AlfaCS_EQ_WSPartnerReport11", "2010-07-13", BigDecimal.valueOf(55.88)));
        basicService.addWebService(getTestWebService(3, "AlfaCS_EQ_WSPartnerReportHistory11", "2011-01-08", BigDecimal.valueOf(560.00)));
        basicService.addWebService(getTestWebService(4, "AlfaCS_EQ_WSProductCatalogPackageTarif10", "2008-02-01", BigDecimal.valueOf(200)));
        basicService.addWebService(getTestWebService(5, "AlfaCS_EQ_WSStatementDepositInfo13", "2009-12-01", BigDecimal.valueOf(546.566)));
        basicService.addWebService(getTestWebService(6, "AlfaCS_EQ_WSAccountCloseRestriction10", "2015-08-15", BigDecimal.valueOf(100)));
        basicService.addWebService(getTestWebService(7, "AlfaCS_EQ_WSProductCatalogDocGroup10", "2016-07-29", BigDecimal.valueOf(42.4)));
    }

    @Test
    public void findAllByNameEndsWith10() throws Exception {
        List<WebService> webServices = advancedService.findAllByNameEndsWith10();
        assertTrue(webServices.size() == 3);
        webServices.forEach(webService -> assertTrue(webService.getName().endsWith("10")));
    }

    @Test
    public void getByPriceGreaterOrEquals100() throws Exception {
        List<WebService> webServices = advancedService.getByPriceGreaterOrEquals100();
        assertTrue(webServices.size() == 5);
        webServices.forEach(webService -> assertTrue(webService.getPrice().compareTo(BigDecimal.valueOf(100)) >= 0));
    }

    @Test
    public void nativeQueryNameEndsWith13() throws Exception {
        List<WebService> webServices = advancedService.nativeQueryNameEndsWith13();
        assertTrue(webServices.size() == 1);
        webServices.forEach(webService -> assertTrue(webService.getName().endsWith("13")));
    }

    @Test
    public void getAllWithJsonData() throws Exception {
        List<WebService> webServices = advancedService.nativeQueryPriceEquals100(BigDecimal.valueOf(100));
        assertTrue(webServices.size() == 2);
        webServices = advancedService.nativeQueryPriceEquals100(BigDecimal.valueOf(200));
        assertTrue(webServices.size() == 1);
    }

    private WebService getTestWebService(int id, String name, String date, BigDecimal price) throws Exception {
        WebService webService = new WebService();
        webService.setId(id);
        webService.setName(name);
        webService.setDateContract(date);
        webService.setPrice(price);

        MyJson json = new MyJson();
        json.setId(webService.getId());
        json.setDateContract(webService.getDateContract());
        json.setName(webService.getName());
        json.setPrice(webService.getPrice());
        webService.setJsonData(json);
        return webService;
    }
}