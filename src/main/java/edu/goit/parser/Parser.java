package edu.goit.parser;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Parser<T> {
    protected final Class<T> modelClass;

    public abstract List<T> parse(InputStream inputStream);

    protected abstract String getExtension();

    @SneakyThrows
    public List<T> parse(Path path){
        return parse(Files.newInputStream(path));
    }
}
