package com.example.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Kirill Nigmatullin
 */
public class MyJson {

    @JsonProperty("id")
    Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("dateContract")
    String dateContract;
    @JsonProperty("price")
    BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateContract() {
        return dateContract;
    }

    public void setDateContract(String dateContract) {
        this.dateContract = dateContract;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
