package edu.goit.parser.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class CsvTimestampConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Integer.parseInt(value)), ZoneOffset.UTC);
    }
}
