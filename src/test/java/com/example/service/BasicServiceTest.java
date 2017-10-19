package com.example.service;

import com.example.domain.WebService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
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
public class BasicServiceTest {

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
    public void addWebService() throws Exception {
        assertFalse(basicService.exists(testId));
        basicService.addWebService(getTestWebService(testId, testWSName, testDate, testPrice));
        assertTrue(basicService.exists(testId));
        WebService webService = basicService.findOne(testId);
        assertNotNull(webService);
        assertEquals(webService.getName(), testWSName);
        assertEquals(webService.getDateContract(), testDate);
        assertEquals(webService.getPrice(), testPrice);
    }

    @Test
    public void findOne() throws Exception {
        WebService webService = basicService.findOne(1);
        assertEquals(webService.getId(), 1);
        assertEquals(webService.getName(), "AlfaCS_EQ_WSPartnerBaseInfo22");
        assertEquals(webService.getDateContract(), "22-05-2005");
        assertEquals(webService.getPrice(), BigDecimal.valueOf(100));
    }

    @Test
    public void getWebServices() throws Exception {
        Iterable<WebService> webServices = basicService.getWebServices();
        assertNotNull(webServices);
        List<WebService> webServiceList = new ArrayList<>();
        webServices.forEach(webServiceList::add);
        assertTrue(webServiceList.size() == 7);
    }

    @Test
    public void getWebServices1() throws Exception {
        Iterable<WebService> webServices = basicService.getWebServices(new Sort(Sort.Direction.ASC, "id"));
        assertNotNull(webServices);
        List<WebService> webServiceList = new ArrayList<>();
        webServices.forEach(webServiceList::add);
        assertTrue(webServiceList.size() == 7);
        assertTrue(webServiceList.get(0).getId() == 1);

        webServices = basicService.getWebServices(new Sort(Sort.Direction.DESC, "id"));
        webServiceList = new ArrayList<>();
        webServices.forEach(webServiceList::add);
        assertTrue(webServiceList.get(0).getId() == 7);
    }

    @Test
    public void deleteAll() throws Exception {
        basicService.deleteAll();
        assertEquals(basicService.getWebServices(), new ArrayList<>());
    }

    @Test
    public void deleteById() throws Exception {
        assertTrue(basicService.exists(1));
        basicService.deleteById(1);
        assertFalse(basicService.exists(1));
    }

    @Test
    public void exists() throws Exception {
        assertTrue(basicService.exists(1));
        assertFalse(basicService.exists(100));
    }

    @Test
    public void getWithCriteria() throws Exception {
        List<WebService> webServicesCriteria = new ArrayList<>();
        basicService.getWithCriteria("10").forEach(webServicesCriteria::add);
        assertTrue(webServicesCriteria.size() == 3);
    }

    private WebService getTestWebService(int id, String name, String date, BigDecimal price) throws Exception {
        WebService webService = new WebService();
        webService.setId(id);
        webService.setName(name);
        webService.setDateContract(date);
        webService.setPrice(price);
        return webService;
    }
}