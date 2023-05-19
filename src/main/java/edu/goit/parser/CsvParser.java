package edu.goit.parser;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvParser<T> extends Parser<T>{

    private final static String EXTENSION = "csv";

    public CsvParser(Class<T> modelClass) {
        super(modelClass);
    }

    @SneakyThrows
    @Override
    public List<T> parse(InputStream inputStream) {
        try (Reader reader = new BufferedReader(new InputStreamReader(inputStream))){
            return new CsvToBeanBuilder<T>(reader).withType(modelClass).build().parse();
        }
    }

    @Override
    protected String getExtension() {
        return EXTENSION;
    }

}
