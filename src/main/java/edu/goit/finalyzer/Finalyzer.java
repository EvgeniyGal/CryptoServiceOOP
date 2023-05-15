package edu.goit.finalyzer;

import edu.goit.entity.PairResult;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Function;

public abstract class Finalyzer implements Function<Map<String, Double>, Optional<PairResult>> {

    final static Comparator<Map.Entry<String, Double>> COMPARATOR =
            Map.Entry.comparingByValue(Comparator.comparingDouble(v -> v));

    protected Optional<PairResult> compareAndGet(Map<String, Double> map,
                                                 Comparator<Map.Entry<String, Double>> comparator) {
        return map.entrySet().stream().min(comparator).map(PairResult::new);
    }

    protected Optional<PairResult> normalize(Map<String, Double> map, OptionalDouble normalizeFrom) {
        if (normalizeFrom.isEmpty()) return Optional.empty();
        Comparator<Map.Entry<String, Double>> comparator =
                Comparator.comparingDouble(v -> Math.abs(normalizeFrom.getAsDouble() - v.getValue()));
        return map.entrySet().stream().min(comparator).map(PairResult::new);
    }
}
