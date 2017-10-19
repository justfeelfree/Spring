package com.example.domain;

import com.example.service.AdvancedService;
import com.example.service.BasicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
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
public class WebServiceTest {

    @Autowired
    private BasicService basicService;

    @Autowired
    private AdvancedService advancedService;

    @Test
    public void basicServiceTest() throws Exception {
        String testWSName = "WSTest10";
        String testDate = "15-05-2011";
        int testId = 100;
        BigDecimal testPrice = BigDecimal.valueOf(200);

        basicService.deleteAll();

        WebService webService = new WebService();
        webService.setId(testId);
        webService.setName(testWSName);
        webService.setDateContract(testDate);
        webService.setPrice(testPrice);

        basicService.addWebService(webService);

        assertTrue(basicService.exists(testId));
        WebService foundWebService = basicService.findOne(testId);
        assertEquals(foundWebService.getDateContract(), testDate);
        assertEquals(foundWebService.getName(), testWSName);
        assertEquals(foundWebService.getPrice(), testPrice);

        Iterable<WebService> webServices = basicService.getWebServices(new Sort(Sort.DEFAULT_DIRECTION, "id"));
        assertNotNull(webServices);

        webServices = basicService.getWebServices();
        assertNotNull(webServices);

        List<WebService> webServicesCriteria = new ArrayList<>();
        basicService.getWithCriteria("10").forEach(webServicesCriteria::add);
        assertTrue(webServicesCriteria.size() == 1);

        basicService.deleteById(testId);

        assertFalse(basicService.exists(testId));
    }

    @Test
    public void advancedServiceTest() throws Exception {
        String testWSName = "WSTest10";
        String testDate = "15-05-2011";
        int testId = 100;
        BigDecimal testPrice = BigDecimal.valueOf(200);
        basicService.deleteAll();

        WebService webService = new WebService();
        webService.setId(testId);
        webService.setName(testWSName);
        webService.setDateContract(testDate);
        webService.setPrice(testPrice);
        basicService.addWebService(webService);

        List<WebService> webServices = advancedService.findAllByName();
        assertTrue(webServices.size() == 1);
        webServices = advancedService.simpleQuery();
        assertTrue(webServices.size() == 1);
        webServices = advancedService.nativeQuery();
        assertFalse(webServices.size() == 1);
        basicService.deleteAll();

        webService.setName("WSTest11");
        basicService.addWebService(webService);
        webServices = advancedService.findAllByName();
        assertFalse(webServices.size() == 1);
        webServices = advancedService.nativeQuery();
        assertFalse(webServices.size() == 1);
        basicService.deleteAll();

        webService.setPrice(BigDecimal.ONE);
        basicService.addWebService(webService);
        webServices = advancedService.simpleQuery();
        assertFalse(webServices.size() == 1);
        basicService.deleteAll();

        webService.setName("WSTest13");
        basicService.addWebService(webService);
        webServices = advancedService.findAllByName();
        assertFalse(webServices.size() == 1);
        webServices = advancedService.nativeQuery();
        assertTrue(webServices.size() == 1);
        basicService.deleteAll();

        ObjectMapper mapper = new ObjectMapper();
        String json;
        json = mapper.writeValueAsString(webService);
        webService.setJsonData(json);
        basicService.addWebService(webService);

        WebService currentService = mapper.readValue(basicService.findOne(testId).getJsonData(), WebService.class);
        assertEquals(currentService.getPrice(), BigDecimal.ONE);
    }

}

