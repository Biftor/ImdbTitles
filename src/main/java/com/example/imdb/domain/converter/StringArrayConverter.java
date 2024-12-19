package com.example.imdb.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StringArrayConverter implements AttributeConverter<String[], String> {

    private static final String DELIMITER = ",";

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        return attribute != null ? String.join(DELIMITER, attribute) : null;
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        return dbData != null ? dbData.split(DELIMITER) : null;
    }
}
