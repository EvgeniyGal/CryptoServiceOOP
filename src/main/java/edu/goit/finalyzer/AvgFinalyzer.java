package edu.goit.finalyzer;

import edu.goit.entity.PairResult;

import java.util.Map;
import java.util.Optional;

public class AvgFinalyzer extends Finalyzer{
    @Override
    public Optional<PairResult> apply(Map<String, Double> map) {
        return normalize(map,map.values().stream().mapToDouble(v->v).average());
    }
}
