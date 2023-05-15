package edu.goit.finalyzer;

import edu.goit.entity.PairResult;

import java.util.Map;
import java.util.Optional;

public class MaxFinalizer extends Finalyzer {
    @Override
    public Optional<PairResult> apply(Map<String, Double> map) {
        return compareAndGet(map, COMPARATOR.reversed());
    }
}
