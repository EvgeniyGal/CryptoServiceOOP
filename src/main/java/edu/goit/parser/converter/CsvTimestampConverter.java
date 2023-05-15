package edu.goit.parser.converter;

import com.opencsv.bean.AbstractBeanField;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CsvTimestampConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(value)), ZoneOffset.UTC);
    }
}
