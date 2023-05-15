package client;

import edu.goit.analyzer.Analyzer;
import edu.goit.entity.Crypto;
import edu.goit.entity.PairResult;
import edu.goit.finalyzer.Finalyzer;
import edu.goit.parser.ParserFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CryptoServiceClient {

    private final static String JAR = "jar";
    private static final String PATH_TO_FILES = "prices";

    private final Map<String, List<Crypto>> cryptos;


    public static CryptoServiceClient of() {
        return of(getListFilesFromResources(PATH_TO_FILES).stream());
    }

    @SneakyThrows
    public static CryptoServiceClient of(String path) {
        return of(Files.list(Paths.get(path)));
    }

    private static CryptoServiceClient of(Stream<Path> files) {
        ParserFactory<Crypto> parserFactory = new ParserFactory<>(Crypto.class);
        Map<String, List<Crypto>> collect =
                files.flatMap(p -> parserFactory.getInstances(p).parse(p).stream())
                        .collect(Collectors.groupingBy(Crypto::getSymbol));
        return new CryptoServiceClient(collect);
    }

    @SneakyThrows
    private static List<Path> getListFilesFromResources(String resourcesDirName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URI uri = Objects.requireNonNull(classLoader.getResource(resourcesDirName)).toURI();
        Path mypath = JAR.equalsIgnoreCase(uri.getScheme()) ?
                FileSystems.newFileSystem(uri, Collections.emptyMap()).getPath(resourcesDirName) :
                Paths.get(uri);
        return Files.walk(mypath, 1).filter(Files::isRegularFile).collect(Collectors.toList());
    }

    public PairResult applyFor(Analyzer func1, Finalyzer func2) {
        Map<String, Double> result = cryptos.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> func1.apply(v.getValue())));
        return func2.apply(result).orElseThrow(()-> new RuntimeException("Calculation exception"));
    }

}
