package edu.goit.parser.converter;

import com.opencsv.bean.AbstractBeanField;

public class CsvPriceConverter extends AbstractBeanField<Double, Integer> {
    @Override
    protected Double convert(String value) {
        return Double.valueOf(value);
    }
}
