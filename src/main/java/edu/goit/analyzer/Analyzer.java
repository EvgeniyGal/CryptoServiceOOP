package edu.goit.analyzer;

import edu.goit.entity.Crypto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public abstract class Analyzer implements Function<Iterable<Crypto>, Double> {

    private final LocalDateTime start, end;


    public Double apply(Iterable<Crypto> cryptos){

        return calculate(filterByDate(cryptos, start, end));
    }

    protected abstract Double calculate(Stream<Crypto> cryptoStream);

    protected DoubleSummaryStatistics getStatistics(Stream<Crypto> cryptoStream){
        return cryptoStream.mapToDouble(Crypto::getPrice).summaryStatistics();
    }

    private Stream<Crypto> filterByDate(Iterable<Crypto> cryptos, LocalDateTime start, LocalDateTime end){
        return StreamSupport.stream(cryptos.spliterator(), false)
                .filter(crypto -> crypto.getTimestamp().isAfter(start) && crypto.getTimestamp().isBefore(end));
    }

}
