package edu.goit.parser;

import lombok.SneakyThrows;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ParserFactory<T> {
    private final Map<String, Parser<T>> instances;

    @SneakyThrows
    public ParserFactory(Class<T> modelClass) {
        Set<Class<?>> subClasses = new Reflections(ParserFactory.class.getPackage().getName())
                .get(Scanners.SubTypes.of(Parser.class).asClass());
        this.instances = new HashMap<>(subClasses.size());
        for (Class subClass : subClasses) {
            Parser newInstance = (Parser) subClass.getConstructor(Class.class).newInstance(modelClass);
            this.instances.put(newInstance.getExtension(), newInstance);
        }
    }

    public  Parser<T> getInstances(Path path) {
        String fileName = path.getFileName().toString();
        return Optional.of(fileName)
                .filter(filename->fileName.contains("."))
                .map(filename->fileName.substring(fileName.lastIndexOf(".") +1).toLowerCase())
                .map(instances::get)
                .orElseThrow(()-> new RuntimeException("Not supported yet!"));
    }
}
