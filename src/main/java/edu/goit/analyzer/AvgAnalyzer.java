package edu.goit.analyzer;

import edu.goit.entity.Crypto;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class AvgAnalyzer extends Analyzer {
    public AvgAnalyzer(LocalDateTime start, LocalDateTime end) {
        super(start, end);
    }

    @Override
    protected Double calculate(Stream<Crypto> cryptoStream) {
        return getStatistics(cryptoStream).getAverage();
    }
}
