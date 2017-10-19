package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="web_service", schema = "public")
@NamedQuery(name="WebService.findAllByNameAndPriceCondition", query = "SELECT w FROM WebService w WHERE w.name like CONCAT('%', 10)")
@JsonIgnoreProperties("jsonData")
public class WebService {

    @Transient
    static final DateFormat getDF = new SimpleDateFormat("dd-MM-yyyy");
    @Transient
    final static DateFormat setDF = new SimpleDateFormat("yyyy-MM-dd");

    @Id
    @Column(name="id")
    int id;
    @Column(name="name")
    String name;
    @Column(name="date_contract")
    Date dateContract;
    @Column(name="price")
    BigDecimal price;
    @Column(name="json_data")
    String jsonData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateContract() {
        return getDF.format(dateContract);
    }

    public void setDateContract(String dateContract) throws Exception {
        System.out.println("DATA string: " + dateContract);
        Date contract;

        if (dateContract.matches("\\d{4}[-/]\\d{2}[-/]\\d{2}")) {
            System.out.println("PARSE yyyy-MM-dd");
            contract = setDF.parse(dateContract);
        } else if (dateContract.matches("\\d{2}[-/]\\d{2}[-/]\\d{4}")){
            System.out.println("PARSE dd-MM-yyyy");
            contract = getDF.parse(dateContract);
        } else {
            throw new ParseException("Error. Can't parse " + dateContract, 0);
        }

        if (contract.after(new Date(System.currentTimeMillis()))) {
            throw new Exception(String.format("Error. Date before [%s] has not yet come", dateContract));
        }
        if (contract.before(getDF.parse("01-01-2000"))) {
            throw new Exception(String.format("Error. DateContract before 2000 is out of date", dateContract));
        }
        this.dateContract = contract;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public WebService() {
    }

    public String toString() {
        return String.format("Student[id: [%d],\tname: [%s],\tdate of contract: [%s]], price: [%s]", getId(), getName(), getDateContract(), getPrice());
    }
}
