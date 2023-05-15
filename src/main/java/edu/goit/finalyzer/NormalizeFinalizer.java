package edu.goit.finalyzer;

import edu.goit.entity.PairResult;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.TreeSet;

public class NormalizeFinalizer extends Finalyzer{
    @Override
    public Optional<PairResult> apply(Map<String, Double> map) {
        if (map.isEmpty()) return Optional.empty();
        TreeSet<Double> set = new TreeSet<>(map.values());
        double result = (set.last()-set.first())/set.first();
        return normalize(map, OptionalDouble.of(result));
    }
}
