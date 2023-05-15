package edu.goit.analyzer;

import edu.goit.entity.Crypto;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.stream.Stream;

public class NormalizeAnalyzer extends Analyzer{
    public NormalizeAnalyzer(LocalDateTime start, LocalDateTime end) {
        super(start, end);
    }

    @Override
    protected Double calculate(Stream<Crypto> cryptoStream) {
        DoubleSummaryStatistics statistics = getStatistics(cryptoStream);
        return (statistics.getMax()-statistics.getMin())/statistics.getMin();
    }
}
