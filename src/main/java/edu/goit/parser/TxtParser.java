package edu.goit.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtParser<T> extends Parser<T> {

    private final static String EXTENSION = "txt";
    private final static ObjectMapper MAPPER = new ObjectMapper();

    public TxtParser(Class<T> modelClass) {
        super(modelClass);
    }

    @Override
    public List<T> parse(InputStream inputStream) {
        try (Stream<String> stream = new BufferedReader(new InputStreamReader(inputStream)).lines()){
            return stream.map(line -> parse(line, modelClass)).collect(Collectors.toList());
        }
    }

    @SneakyThrows
    private T parse(String line, Class<T> modelClass){
        return MAPPER.readValue(line,modelClass);
    }

    @Override
    protected String getExtension() {
        return EXTENSION;
    }
}
