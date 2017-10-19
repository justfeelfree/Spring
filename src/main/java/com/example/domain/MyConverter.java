package com.example.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.validation.constraints.NotNull;

/**
 * @author Kirill Nigmatullin
 */

@Converter
public class MyConverter implements AttributeConverter<MyJson, String> {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @NotNull
    public String convertToDatabaseColumn(@NotNull MyJson myCustomObject) {
        try {
            return objectMapper.writeValueAsString(myCustomObject);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    @NotNull
    public MyJson convertToEntityAttribute(@NotNull String databaseDataAsJSONString) {
        try {
            return objectMapper.readValue(databaseDataAsJSONString, MyJson.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
